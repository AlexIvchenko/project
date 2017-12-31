package com.github.habiteria.core.domain.model;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
public class ScheduledHabit {
    private final Habit habit;
    private final boolean required;
    private final boolean verifiable;
    private final Status status;
    private final int repeat;
    private final LocalDate date;

    public ScheduledHabit(Habit habit, boolean required, boolean verifiable, Status status, int repeat, LocalDate date) {
        this.habit = habit;
        this.required = required;
        this.verifiable = verifiable;
        this.status = status;
        this.repeat = repeat;
        this.date = date;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public boolean isFail() {
        return status == Status.FAIL;
    }

    public boolean isUnverified() {
        return status == Status.UNVERIFIED;
    }

}
