package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.domain.service.fetcher.StrictFetcher;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.exceptions.client.IllegalCalendarRecordStateTransitionException;
import com.github.habiteria.core.exceptions.client.ResourceNotFoundException;
import com.github.habiteria.core.exceptions.client.TryToVerifyNotVerifiableHabitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class TrackerImpl implements Tracker {
    private final StrictFetcher fetcher;
    private final Scheduler scheduler;

    public TrackerImpl(StrictFetcher fetcher, Scheduler scheduler) {
        this.fetcher = fetcher;
        this.scheduler = scheduler;
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
        log.info("current size: " + scheduled.size());
        return scheduled;
    }

    @Override
    public ScheduledHabit perform(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException {
        Habit habit = fetcher.fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        // TODO validation
        record.setStatus(Status.SUCCESS);
        scheduler.update(record);
        // TODO change person state or publish event
        return build(habit, record);
    }

    @Override
    public ScheduledHabit fail(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException {
        Habit habit = fetcher.fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        record.setStatus(Status.FAIL);
        scheduler.update(record);
        return build(habit, record);
    }

    @Override
    public ScheduledHabit undo(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException {
        Habit habit = fetcher.fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        record.setStatus(Status.UNVERIFIED);
        scheduler.update(record);
        return build(habit, record);
    }

    private ScheduledHabit build(Habit habit, CalendarRecord record) {
        LocalDateTime now = LocalDateTime.now();
        boolean verifiable = !now.isBefore(record.getStartVerifying()) && !now.isAfter(record.getEndVerifying());
        return new ScheduledHabit(habit, record.isRequired(), verifiable, record.getStatus(), record.getRepeat());
    }

    private void assertVerifiable(CalendarRecord record) throws TryToVerifyNotVerifiableHabitException {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(record.getStartVerifying()) || now.isAfter(record.getEndVerifying())) {
            throw new TryToVerifyNotVerifiableHabitException(record);
        }
    }
}
