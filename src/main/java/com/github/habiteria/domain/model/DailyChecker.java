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
public class DailyChecker extends HabitChecker {
    public DailyChecker() {
        super(CheckerType.DAILY);
    }

    public DailyChecker(Habit habit) {
        super(habit, CheckerType.DAILY);
    }
}
