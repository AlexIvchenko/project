package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Alex Ivchenko
 */
public class DayGenerator {
    public CalendarRecord generate(Habit habit, LocalDate date, int repeat) {
        CalendarRecord record = new CalendarRecord();
        record.setHabit(habit);
        record.setRepeat(repeat);

        record.setStartDoing(date.atTime(LocalTime.MIN));
        record.setEndDoing(date.atTime(LocalTime.MAX));

        record.setStartVerifying(date.atTime(LocalTime.MIN));
        record.setEndVerifying(date.plusDays(1).atTime(LocalTime.MAX));

        if (record.getEndVerifying().isBefore(LocalDateTime.now())) {
            record.setStatus(Status.FAIL);
        } else {
            record.setStatus(Status.UNVERIFIED);
        }

        return record;
    }
}
