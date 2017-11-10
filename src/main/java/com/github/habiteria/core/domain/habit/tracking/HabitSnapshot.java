package com.github.habiteria.core.domain.habit.tracking;

import com.github.habiteria.core.model.Habit;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public class HabitSnapshot {
    private final Habit habit;
    private final LocalDate date;
    private final boolean required;
    private final boolean available;
    private final Status status;

    public HabitSnapshot(Habit habit, LocalDate date, boolean required, boolean available, Status status) {
        this.habit = habit;
        this.date = date;
        this.required = required;
        this.available = available;
        this.status = status;
    }

    public enum Status {
        SUCCESS, FAIL, UNDEFINED
    }

    public Habit getHabit() {
        return habit;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public boolean isFail() {
        return status == Status.FAIL;
    }

    public boolean isUnverified() {
        return status == Status.UNDEFINED;
    }

    public Status getStatus() {
        return status;
    }
}
