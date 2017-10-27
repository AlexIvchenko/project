package com.github.netcracker2017team.project.domain.model;

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
@Table(name = "step")
@Entity
@DiscriminatorColumn(name = "type")
public abstract class Step extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
