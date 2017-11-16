package com.github.habiteria.core.exceptions.client;

import com.github.habiteria.core.entities.Habit;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public class FutureScheduleRetrievingException extends ClientException {
    public FutureScheduleRetrievingException(Habit habit, LocalDate future) {
        super(String.format("unable to retrieve calendar records, date: %s, habit: %s", future, habit));
    }

    public FutureScheduleRetrievingException(Habit habit, int repeat) {
        super(String.format("habit"));
    }
}
