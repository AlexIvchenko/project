package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("user")
public class UserCreatedStep extends Step {
    @ManyToOne
    @JoinColumn(name = "user_created_goal_id")
    private UserCreatedGoal goal;
}
