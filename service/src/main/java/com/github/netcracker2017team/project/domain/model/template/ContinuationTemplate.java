package com.github.netcracker2017team.project.domain.model.template;

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
@Table(name = "continuation_templates")
@Entity
@DiscriminatorColumn(name = "type")
public abstract class ContinuationTemplate {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
