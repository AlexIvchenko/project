package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.exceptions.client.FutureScheduleRetrievingException;
import com.github.habiteria.core.repository.CalendarRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class SchedulerImpl implements Scheduler {
    private final CalendarRecordRepository repository;
    private final Generator generator;

    public SchedulerImpl(CalendarRecordRepository repository, Generator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    @Override
    public CalendarRecord getRecord(Habit habit, int repeat) throws FutureScheduleRetrievingException {
        if (repeat <= 0) {
            throw new IllegalArgumentException("repeat must be greater than 0");
        }
        CalendarRecord generated = generator.getOneRepeat(habit, repeat);
        CalendarRecord loaded = repository.findOne(habit, repeat);
        CalendarRecord result = merge(loaded, generated);
        // TODO
//        throw new FutureScheduleRetrievingException(habit, repeat);
//        throw new SequenceOfRepeatsBrokenException(habit, repeat, last);
        return result;
    }

    @Override
    public CalendarRecord update(CalendarRecord record) {
        if (record.getStatus() == Status.UNVERIFIED) {
            repository.delete(record);
            return record;
        } else {
            return repository.save(record);
        }
    }

    @Override
    public Set<CalendarRecord> findVerifiable(User user) {
        LocalDateTime now = LocalDateTime.now();
        Set<CalendarRecord> generated = generator.getOnlyVerifiableIn(user, now);
        Set<CalendarRecord> loaded = repository.findVerifiableIn(user, now);
        return merge(loaded, generated);
    }

    @Override
    public Set<CalendarRecord> getRecords(Habit habit, LocalDate from, LocalDate to) throws FutureScheduleRetrievingException {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("\"from\" date must not be after than \"to\" date");
        }
        if (from.isAfter(LocalDate.now())) {
            throw new FutureScheduleRetrievingException(habit, from);
        }
        if (to.isAfter(LocalDate.now())) {
            throw new FutureScheduleRetrievingException(habit, to);
        }
        Set<CalendarRecord> generated = generator.getAllBetween(habit, from, to);
        Set<CalendarRecord> loaded = repository.findBetweenByDoingTime(habit, from.atTime(LocalTime.MIN), to.atTime(LocalTime.MAX));
        return merge(loaded, generated);
    }

    private Set<CalendarRecord> merge(Set<CalendarRecord> loaded, Set<CalendarRecord> generated) {
        log.info("merging loaded size: {}, generated size: {}", loaded.size(), generated.size());
        Set<CalendarRecord> result = new HashSet<>(loaded);
        for (CalendarRecord rec : generated) {
            if (!result.contains(rec)) {
                result.add(rec);
            }
        }
        log.info("merged size: " + result.size());
        return result;
    }

    private CalendarRecord merge(CalendarRecord loaded, CalendarRecord generated) {
        return loaded != null ? loaded : generated;
    }
}
