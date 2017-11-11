package com.github.habiteria.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule extends AbstractEntity {
    public Schedule() {

    }

    public Schedule(Habit habit) {
        this.habit = habit;
    }

    @OneToOne(mappedBy = "schedule", optional = false)
    private Habit habit;

    // TODO schedule records (cron)
    // (startDoing, doingDuration, startVerifying, verifyingDuration
}
