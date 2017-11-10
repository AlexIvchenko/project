package com.github.habiteria.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "weekly_checkers")
public class WeeklySchedule extends Schedule {

    @Column(name = "repeats")
    private int repeats;

    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL)
    @JoinColumn(name = "week_days_id")
    private WeekDays days;

    public WeeklySchedule() {
        super(ScheduleType.WEEKLY);
    }

    public WeeklySchedule(Habit habit) {
        super(habit, ScheduleType.WEEKLY);
    }
}
