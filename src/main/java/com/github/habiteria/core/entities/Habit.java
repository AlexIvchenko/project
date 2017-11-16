package com.github.habiteria.core.entities;

import com.github.habiteria.core.entities.imps.UserImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@Table(name = "habits")
@ToString(of = "name", callSuper = false)
public class Habit extends AbstractEntity {
    @ManyToOne(targetEntity = UserImpl.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}
