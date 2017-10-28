package com.github.netcracker2017team.project.domain.model.template;

import com.github.netcracker2017team.project.domain.model.AbstractEntity;
import com.github.netcracker2017team.project.domain.model.TimeUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString(of = "name")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "goal_templates")
@Entity
@DiscriminatorColumn(name = "type")
public abstract class GoalTemplate extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_unit")
    private TimeUnit timeUnit;

    @Column(name = "time_unit_repeats")
    private Integer timeUnitRepeats;
}
