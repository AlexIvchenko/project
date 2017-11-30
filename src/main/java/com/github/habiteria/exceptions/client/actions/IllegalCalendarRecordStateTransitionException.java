package com.github.habiteria.exceptions.client.actions;

import com.github.habiteria.core.entities.CalendarRecord;

/**
 * @author Alex Ivchenko
 */
public class IllegalCalendarRecordStateTransitionException extends IllegalStateTransitionException {
    private final static ActionCode UNDO_UNVERIFIED = new ActionCode(CalendarRecord.class, "unverified", "undo");
    private final static ActionCode PERFORM_VERIFIED = new ActionCode(CalendarRecord.class, "verified", "perform");
    private final static ActionCode FAIL_VERIFIED = new ActionCode(CalendarRecord.class, "verified", "fail");

    private IllegalCalendarRecordStateTransitionException(String message, ActionCode code) {
        super(message, code);
    }
    public static IllegalCalendarRecordStateTransitionException undoWhenUnverified(CalendarRecord record) {
        return new IllegalCalendarRecordStateTransitionException(
                String.format("trying to undo unverified record: %s", record),
                UNDO_UNVERIFIED);
    }

    public static IllegalCalendarRecordStateTransitionException performWhenAlreadyVerified(CalendarRecord record) {
        return new IllegalCalendarRecordStateTransitionException(
                String.format("trying to perform already verified record: %s", record),
                PERFORM_VERIFIED);
    }

    public static IllegalCalendarRecordStateTransitionException failWhenAlreadyVerified(CalendarRecord record) {
        return new IllegalCalendarRecordStateTransitionException(String.format(
                "trying to fail already verified record: %s", record),
                FAIL_VERIFIED);
    }
}
