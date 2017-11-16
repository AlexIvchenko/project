package com.github.habiteria.core.exceptions.client;

import com.github.habiteria.core.entities.CalendarRecord;

/**
 * @author Alex Ivchenko
 */
public class TryToVerifyNotVerifiableHabitException extends ClientException {
    public TryToVerifyNotVerifiableHabitException(CalendarRecord record) {
        super(String.format("habit %s # %s can't be verified, verifiable interval: [%s, %s]",
                record.getHabit(), record.getRepeat(),
                record.getStartVerifying(), record.getEndVerifying()));
    }
}
