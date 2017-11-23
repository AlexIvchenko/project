package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.utils.LocalDateRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Component("dailyGenerator")
public class DailyGeneratorImpl extends AbstractGenerator {
    public DailyGeneratorImpl(HabitRepository habitRepository) {
        super(habitRepository);
    }

    @Override
    public Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) {
        log.info("getting all between {} and {}", from, to);
        int repeat = 1;
        Set<CalendarRecord> generated = new HashSet<>();
        LocalDate habitStarts = habit.getSchedule().getStart().toLocalDate();
        if (habitStarts.isAfter(from)) {
            from = habitStarts;
        }
        for (LocalDate date : new LocalDateRange(from, to)) {
            CalendarRecord record = new CalendarRecord();
            record.setHabit(habit);
            record.setRepeat(repeat++);

            LocalDateTime startDoing = date.atTime(LocalTime.MIN);
            LocalDateTime startVerifying = date.atTime(LocalTime.MIN);
            LocalDateTime endDoing = date.atTime(LocalTime.MAX);
            LocalDateTime endVerifying = date.plusDays(1).atTime(LocalTime.MAX);

            record.setStartDoing(startDoing);
            record.setEndDoing(endDoing);

            record.setStartVerifying(startVerifying);
            record.setEndVerifying(endVerifying);

            if (endVerifying.isBefore(LocalDateTime.now())) {
                record.setStatus(Status.FAIL);
            } else {
                record.setStatus(Status.UNVERIFIED);
            }

            generated.add(record);
        }
        log.info("generated {} records", generated.size());
        return generated;
    }
}
