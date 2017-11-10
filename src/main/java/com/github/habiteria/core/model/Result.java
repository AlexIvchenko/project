package com.github.habiteria.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "results")
public class Result extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false)
    private Status status;

    public Result() {
    }

    private Result(Habit habit, LocalDate date, Status status) {
        this.habit = habit;
        this.date = date;
        this.status = status;
    }

    public enum Status {
        SUCCESS, FAIL
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public boolean isFail() {
        return status == Status.FAIL;
    }

    public static Result fail(Habit habit) {
        return new Result(habit, LocalDate.now(), Status.FAIL);
    }

    public static Result success(Habit habit) {
        return new Result(habit, LocalDate.now(), Status.SUCCESS);
    }

    public static Result fail(Habit habit, LocalDate date) {
        return new Result(habit, date, Status.FAIL);
    }

    public static Result success(Habit habit, LocalDate date) {
        return new Result(habit, date, Status.SUCCESS);
    }
}
