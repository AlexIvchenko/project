package com.github.netcracker2017team.project.domain.model.template.user;

import com.github.netcracker2017team.project.domain.model.template.StepTemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(callSuper = true, exclude = "goal")
@Entity
@DiscriminatorValue("doer")
public class UserStepTemplate extends StepTemplate {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_goal_template_id")
    private UserGoalTemplate goal;
}
