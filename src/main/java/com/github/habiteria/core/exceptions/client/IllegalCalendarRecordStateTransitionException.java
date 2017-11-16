package com.github.habiteria.core.exceptions.client;

import com.github.habiteria.core.entities.CalendarRecord;

/**
 * @author Alex Ivchenko
 */
public class IllegalCalendarRecordStateTransitionException extends IllegalStateTransitionException {
    private IllegalCalendarRecordStateTransitionException(String message) {
        super(message);
    }
    public static IllegalCalendarRecordStateTransitionException undoWhenUnferified(CalendarRecord record) {
        return new IllegalCalendarRecordStateTransitionException(String.format("trying to undo unverified record: %s", record));
    }

    public static IllegalCalendarRecordStateTransitionException performWhenAlreadyVerified(CalendarRecord record) {
        return new IllegalCalendarRecordStateTransitionException(String.format("trying to perform already verified record: %s", record));
    }

    public static IllegalCalendarRecordStateTransitionException failWhenAlreadyVnferified(CalendarRecord record) {
        return new IllegalCalendarRecordStateTransitionException(String.format("trying to fail already verified record: %s", record));
    }
}
