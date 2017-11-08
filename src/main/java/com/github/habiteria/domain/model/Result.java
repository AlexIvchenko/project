package com.github.habiteria.domain.model;

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
    private Status status = Status.UNDEFINED;

    public Result() {
    }

    public Result(Habit habit, Status status) {
        this.habit = habit;
        this.status = status;
        date = LocalDate.now();
    }

    public enum Status {
        SUCCESS, FAIL, UNDEFINED
    }
}
