package com.github.netcracker2017team.project.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "user_created_step")
public class UserCreatedStep extends Step {
    @ManyToOne
    @JoinColumn(name = "user_created_goal_id", nullable = false)
    private UserCreatedGoal goal;
}
