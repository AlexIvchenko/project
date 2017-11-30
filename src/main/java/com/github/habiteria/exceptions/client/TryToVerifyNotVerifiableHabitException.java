package com.github.habiteria.exceptions.client;

import com.github.habiteria.core.entities.CalendarRecord;

/**
 * @author Alex Ivchenko
 */
// TODO make IllegalActionException
public class TryToVerifyNotVerifiableHabitException extends RuntimeException {
    public TryToVerifyNotVerifiableHabitException(CalendarRecord record) {
        super(String.format("habit %s # %s can't be verified, verifiable interval: [%s, %s]",
                record.getHabit(), record.getRepeat(),
                record.getStartVerifying(), record.getEndVerifying()), null);
    }
}
