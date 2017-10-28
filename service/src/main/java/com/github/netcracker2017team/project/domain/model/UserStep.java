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
public class UserStep extends StepTemplate {
    @ManyToOne
    @JoinColumn(name = "user_goal_id")
    private UserGoal goal;
}
