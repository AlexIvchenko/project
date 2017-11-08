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
@Table(name = "habits")
public class Habit extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "checker_type")
    private CheckerType checkerType;

    @OneToOne(mappedBy = "habit", cascade = CascadeType.ALL)
    @JoinColumn(name = "checker_id")
    private HabitChecker checker;
}
