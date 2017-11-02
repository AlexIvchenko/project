package com.github.netcracker2017team.project.domain.model;

import com.github.netcracker2017team.project.domain.model.template.UserStepTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class PersonalStep extends Step {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_step_template_id")
    private UserStepTemplate template;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personal_goal_id")
    private PersonalGoal goal;

    public PersonalStep(UserStepTemplate template, PersonalGoal goal) {
        this.template = template;
        this.goal = goal;
    }
}
