package com.github.habitaria.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "weekly_habits")
public class WeeklyChecker extends HabitChecker {

    @Column(name = "repeats")
    private int repeats;

    @OneToOne(mappedBy = "checker", cascade = CascadeType.ALL)
    @JoinColumn(name = "week_days_id")
    private WeekDays days;
}
