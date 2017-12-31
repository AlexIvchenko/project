package com.github.habiteria.exceptions.client;

import com.github.habiteria.core.entities.Habit;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
// TODO make IllegalActionException
public class FutureScheduleRetrievingException extends RuntimeException {
    public FutureScheduleRetrievingException(LocalDate future) {
        super(String.format("unable to retrieve calendar records, date: %s", future));
    }

    public FutureScheduleRetrievingException(LocalDateTime future) {
        super(String.format("unable to retrieve calendar records, timt: %s", future));
    }

    public FutureScheduleRetrievingException(Habit habit, int repeat) {
        super(String.format("unable to retrieve calendar record #%s for habit %s", repeat, habit));
    }
}
