package com.github.habiteria.core.entities.imps;

import com.github.habiteria.core.entities.AbstractEntity;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Schedule.Type type;

    public ScheduleImpl() {
    }

    // TODO schedule records (cron)
    // (startDoing, doingDuration, startVerifying, verifyingDuration
}
