package com.github.netcracker2017team.project.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goal")
    private Set<UserCreatedStep> children;
}
