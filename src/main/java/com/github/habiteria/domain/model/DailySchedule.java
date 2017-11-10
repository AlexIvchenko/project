package com.github.habiteria.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "daily_checkers")
public class DailySchedule extends Schedule {
    public DailySchedule() {
        super(ScheduleType.DAILY);
    }

    public DailySchedule(Habit habit) {
        super(habit, ScheduleType.DAILY);
    }
}
