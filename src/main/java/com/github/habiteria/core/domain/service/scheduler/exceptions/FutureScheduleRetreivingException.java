package com.github.habiteria.core.domain.service.scheduler.exceptions;

import com.github.habiteria.core.entities.Habit;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public class FutureScheduleRetreivingException extends RuntimeException {
    public FutureScheduleRetreivingException(Habit habit, LocalDate future) {
        super(String.format("unable to retrieve calendar records, date: %s, habit: %s", future, habit));
    }

    public FutureScheduleRetreivingException(Habit habit, int repeat) {
        super(String.format("habit"));
    }
}
