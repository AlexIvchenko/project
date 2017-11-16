package com.github.habiteria.core.exceptions.server;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;

/**
 * @author Alex Ivchenko
 */
public class SequenceOfRepeatsBrokenException extends ServerException {
    public SequenceOfRepeatsBrokenException(Habit habit, int repeat, CalendarRecord last) {
        super(String.format("have broken sequence for habit %s, " +
                "there is no record with repeat %s but last record has %s repeat", habit, repeat, last.getRepeat()));
    }
}
