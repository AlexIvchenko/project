package com.github.habiteria.core.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

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

    @Column(name = "start")
    private LocalDateTime start;

    // TODO schedule records (cron)
    // (startDoing, doingDuration, startVerifying, verifyingDuration
}
