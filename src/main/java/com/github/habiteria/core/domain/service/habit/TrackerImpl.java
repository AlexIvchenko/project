package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final Scheduler scheduler;


    public TrackerImpl(UserRepository userRepository, HabitRepository habitRepository, Scheduler scheduler) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.scheduler = scheduler;
    }

    @Override
    public Set<ScheduledHabit> getCurrentHabitList(Long userId) {
        User user = fetchUser(userId);
        Set<CalendarRecord> records = scheduler.findVerifiable(user);
        Set<ScheduledHabit> scheduled = new HashSet<>();
        for (CalendarRecord record : records) {
            scheduled.add(build(record.getHabit(), record));
        }
        log.info("current size: " + scheduled.size());
        return scheduled;
    }

    @Override
    public ScheduledHabit perform(Long habitId, int repeat) {
        Habit habit = fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        // TODO validation
        record.setStatus(Status.SUCCESS);
        scheduler.update(record);
        // TODO change person state or publish event
        return build(habit, record);
    }

    @Override
    public ScheduledHabit fail(Long habitId, int repeat) {
        Habit habit = fetchHabit(habitId);
        CalendarRecord record = scheduler.getRecord(habit, repeat);
        record.setStatus(Status.FAIL);
        scheduler.update(record);
        return build(habit, record);
    }

    @Override
    public ScheduledHabit undo(Long habitId, int repeat) {
        Habit habit = fetchHabit(habitId);
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

    private User fetchUser(Long userId) {
        return userRepository.findOne(userId);
    }

    private Habit fetchHabit(Long habitId) {
        return habitRepository.findOne(habitId);
    }
}
