package com.github.netcracker2017team.project.domain.model.template;

import com.github.netcracker2017team.project.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(of = "name")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "step_templates")
@Entity
@DiscriminatorColumn(name = "type")
public abstract class StepTemplate extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}