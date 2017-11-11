package com.github.habiteria.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "habits")
@ToString(of = "name", callSuper = false)
public class Habit extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(name = "start")
    private LocalDate start;
}
