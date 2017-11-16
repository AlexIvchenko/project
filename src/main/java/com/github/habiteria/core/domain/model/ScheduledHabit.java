package com.github.habiteria.core.domain.model;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;

/**
 * @author Alex Ivchenko
 */
public class ScheduledHabit {
    private final Habit habit;
    private final boolean required;
    private final boolean verifiable;
    private final Status status;
    private final int repeat;

    public ScheduledHabit(Habit habit, boolean required, boolean verifiable, Status status, int repeat) {
        this.habit = habit;
        this.required = required;
        this.verifiable = verifiable;
        this.status = status;
        this.repeat = repeat;
    }

    public Habit getHabit() {
        return habit;
    }

    public boolean isRequired() {
        return required;
    }

    public Status getStatus() {
        return status;
    }

    public int getRepeat() {
        return repeat;
    }

    public boolean isVerifiable() {
        return verifiable;
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
