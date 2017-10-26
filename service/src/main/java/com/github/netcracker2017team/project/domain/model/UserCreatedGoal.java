package com.github.netcracker2017team.project.domain.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "user_created_goal")
public class UserCreatedGoal extends Goal {

    @ManyToOne
    @JoinColumn(name = "user_owner_id", nullable = false)
    private User owner;
}
