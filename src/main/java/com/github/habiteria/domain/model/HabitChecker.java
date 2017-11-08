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
@Table(name = "habit_checkers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HabitChecker extends AbstractEntity {

    @OneToOne
    private Habit habit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CheckerType type;
}
