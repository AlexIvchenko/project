package com.github.netcracker2017team.project.domain.model.template;

import com.github.netcracker2017team.project.domain.model.User;
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
@DiscriminatorValue("user")
public class UserGoalTemplate extends GoalTemplate {

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goal", orphanRemoval = true)
    private Set<UserStepTemplate> steps;
}
