package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Entity
@Table(name = "continuations")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@ToString(callSuper = true)
public abstract class Continuation extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_doer_id")
    private User doer;
}
