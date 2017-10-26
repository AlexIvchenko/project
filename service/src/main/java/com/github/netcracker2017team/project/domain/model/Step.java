package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(of = "name")
@Entity
@Table(name = "step")
public class Step extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
