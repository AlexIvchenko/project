package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Status;
import com.github.habiteria.core.model.User;
import com.github.habiteria.core.repository.CalendarRecordRepository;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class TrackerImpl implements Tracker {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final CalendarRecordRepository calendarRecordRepository;

    public TrackerImpl(UserRepository userRepository, HabitRepository habitRepository, CalendarRecordRepository calendarRecordRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.calendarRecordRepository = calendarRecordRepository;
    }

    @Override
    public Set<ScheduledHabit> getCurrentHabitList(UUID userId) {
        User user = fetchUser(userId);
        LocalDateTime now = LocalDateTime.now();
        Set<CalendarRecord> records = calendarRecordRepository.findVerifiableIn(user, now);
        Set<Habit> habits = habitRepository.findByOwner(user);
        Set<ScheduledHabit> scheduled = new HashSet<>();
        log.info("habits size: " + habits.size());
        log.info("records size: " + records.size());
        for (Habit habit : habits) {
            for (CalendarRecord record : records) {
                if (record.getHabit().equals(habit)) {
                    scheduled.add(build(habit, record));
                }
            }
        }
        log.info("scheduled size: " + scheduled.size());
        return scheduled;
    }

    @Override
    public ScheduledHabit perform(UUID habitId, int repeat) {
        Habit habit = fetchHabit(habitId);
        CalendarRecord record = calendarRecordRepository.findOne(habit, repeat);
        // TODO validation
        record.setStatus(Status.SUCCESS);
        calendarRecordRepository.save(record);
        // TODO change person state or publish event
        return build(habit, record);
    }

    @Override
    public ScheduledHabit fail(UUID habitId, int repeat) {
        Habit habit = fetchHabit(habitId);
        CalendarRecord record = calendarRecordRepository.findOne(habit, repeat);
        record.setStatus(Status.FAIL);
        calendarRecordRepository.save(record);
        return build(habit, record);
    }

    @Override
    public ScheduledHabit undo(UUID habitId, int repeat) {
        Habit habit = fetchHabit(habitId);
        CalendarRecord record = calendarRecordRepository.findOne(habit, repeat);
        record.setStatus(Status.UNVERIFIED);
        calendarRecordRepository.save(record);
        return build(habit, record);
    }

    private ScheduledHabit build(Habit habit, CalendarRecord record) {
        LocalDateTime now = LocalDateTime.now();
        boolean verifiable = !now.isBefore(record.getStartVerifying()) && !now.isAfter(record.getEndVerifying());
        return new ScheduledHabit(habit, record.isRequired(), verifiable, record.getStatus(), record.getRepeat());
    }

    private User fetchUser(UUID userId) {
        return userRepository.findOne(userId.toString());
    }

    private Habit fetchHabit(UUID habitId) {
        return habitRepository.findOne(habitId.toString());
    }
}
