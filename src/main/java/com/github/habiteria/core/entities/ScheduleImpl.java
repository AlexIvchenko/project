package com.github.habiteria.core.entities;

import com.github.habiteria.core.entities.imps.HabitImpl;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = "habit", callSuper = false)
public class ScheduleImpl extends AbstractEntity implements Schedule {

    @OneToOne(mappedBy = "schedule", optional = false, targetEntity = HabitImpl.class)
    private Habit habit;

    @Column(name = "start")
    private LocalDateTime start;

    public ScheduleImpl() {
    }

    // TODO schedule records (cron)
    // (startDoing, doingDuration, startVerifying, verifyingDuration
}
