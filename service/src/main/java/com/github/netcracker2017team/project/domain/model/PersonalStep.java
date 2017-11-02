package com.github.netcracker2017team.project.domain.model;

import com.github.netcracker2017team.project.domain.model.template.UserStepTemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(exclude = "goal")
@Entity
@DiscriminatorValue("personal")
public class PersonalStep extends Step {

    @ManyToOne
    @JoinColumn(name = "user_step_template_id", nullable = false)
    private UserStepTemplate template;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_goal_id")
    private PersonalGoal goal;
}
