package com.github.netcracker2017team.project.domain.model;

import com.github.netcracker2017team.project.domain.model.template.UserContinuationTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "goal")
@NoArgsConstructor
@DiscriminatorValue("personal")
public class PersonalContinuation extends Continuation {

    public PersonalContinuation(User doer, UserContinuationTemplate template, PersonalGoal goal) {
        setDoer(doer);
        this.template = template;
        this.goal = goal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_template_id")
    private UserContinuationTemplate template;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_goal_id")
    private PersonalGoal goal;
}
