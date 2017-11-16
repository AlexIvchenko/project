package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.CalendarRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class SchedulerImpl implements Scheduler {
    private final CalendarRecordRepository recordRepository;
    private final ScheduleGenerator generator;

    public SchedulerImpl(CalendarRecordRepository recordRepository, ScheduleGenerator generator) {
        this.recordRepository = recordRepository;
        this.generator = generator;
    }

    @Override
    public CalendarRecord getRecord(Habit habit, int repeat) {
        generator.generate(habit);
        return recordRepository.findOne(habit, repeat);
    }

    @Override
    public CalendarRecord update(CalendarRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public Set<CalendarRecord> findVerifiable(User user) {
        generator.generateAll(user);
        return recordRepository.findVerifiableIn(user, LocalDateTime.now());
    }

    @Override
    public Set<CalendarRecord> getRecords(Habit habit, LocalDate from, LocalDate to) {
        generator.generate(habit, to.atStartOfDay());
        return recordRepository.findBetween(habit, from.atStartOfDay(), to.plusDays(1).atStartOfDay());
    }
}
