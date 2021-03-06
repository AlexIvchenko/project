package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.domain.service.fetcher.StrictFetcher;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.exceptions.client.actions.IllegalCalendarRecordStateTransitionException;
import com.github.habiteria.exceptions.client.ResourceNotFoundException;
import com.github.habiteria.exceptions.client.TryToVerifyNotVerifiableHabitException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class TrackerImpl implements Tracker {
    private final StrictFetcher fetcher;
    private final Scheduler scheduler;

    public TrackerImpl(StrictFetcher fetcher, Scheduler scheduler) {
        this.fetcher = fetcher;
        this.scheduler = scheduler;
    }

    @Override
    public Set<CalendarRecord> getHabitTracking(Long habitId) {
        return scheduler.findVerifiable(fetcher.fetchHabit(habitId));
    }

    @Override
    public Set<ScheduledHabit> getCurrentHabitList(Long userId)
            throws ResourceNotFoundException, TryToVerifyNotVerifiableHabitException {
        User user = fetcher.fetchUser(userId);
        Set<CalendarRecord> records = scheduler.findVerifiable(user);
        Set<ScheduledHabit> scheduled = new HashSet<>();
        for (CalendarRecord record : records) {
            scheduled.add(build(record.getHabit(), record));
        }
        return scheduled;
    }

    @Override
    public CalendarRecord perform(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException {
        Habit habit = fetcher.fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        assertVerifiable(record);
        if (record.isUnverified()) {
            record.setStatus(Status.SUCCESS);
            scheduler.update(record);
            return record;
        } else {
            throw IllegalCalendarRecordStateTransitionException.performWhenAlreadyVerified(record);
        }
    }

    @Override
    public CalendarRecord fail(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException {
        Habit habit = fetcher.fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        assertVerifiable(record);
        if (record.isUnverified()) {
            record.setStatus(Status.FAIL);
            scheduler.update(record);
            return record;
        } else {
            throw IllegalCalendarRecordStateTransitionException.failWhenAlreadyVerified(record);
        }
    }

    @Override
    public CalendarRecord undo(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException {
        Habit habit = fetcher.fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        assertVerifiable(record);
        if (!record.isUnverified()) {
            record.setStatus(Status.UNVERIFIED);
            scheduler.update(record);
            return record;
        } else {
            throw IllegalCalendarRecordStateTransitionException.undoWhenUnverified(record);
        }
    }

    private ScheduledHabit build(Habit habit, CalendarRecord record) {
        LocalDateTime now = LocalDateTime.now();
        boolean verifiable = !now.isBefore(record.getStartVerifying()) && !now.isAfter(record.getEndVerifying());
        return new ScheduledHabit(habit, record.isRequired(), verifiable, record.getStatus(), record.getRepeat(), record.getDate());
    }

    private void assertVerifiable(CalendarRecord record) throws TryToVerifyNotVerifiableHabitException {
        LocalDateTime now = LocalDateTime.now();
        if (!record.isVerifiableIn(now)) {
            throw new TryToVerifyNotVerifiableHabitException(record);
        }
    }
}
