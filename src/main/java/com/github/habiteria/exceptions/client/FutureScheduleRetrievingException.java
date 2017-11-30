package com.github.habiteria.exceptions.client;

import com.github.habiteria.core.entities.Habit;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
// TODO make IllegalActionException
public class FutureScheduleRetrievingException extends RuntimeException {
    public FutureScheduleRetrievingException(Habit habit, LocalDate future) {
        super(String.format("unable to retrieve calendar records, date: %s, habit: %s", future, habit), null);
    }

    public FutureScheduleRetrievingException(Habit habit, int repeat) {
        super(String.format("habit"));
    }
}
