package com.github.netcracker2017team.project.domain.model;

import com.github.netcracker2017team.project.domain.model.template.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.UserGoalTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@DiscriminatorValue("personal")
public class PersonalGoal extends Goal {

    @ManyToOne
    @JoinColumn(name = "user_goal_template_id")
    private UserGoalTemplate template;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PersonalStep> steps;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PersonalContinuation> continuations;

    public PersonalGoal(User doer, UserGoalTemplate template) {
        setDoer(doer);
        setTemplate(template);
    }

    private void setTemplate(UserGoalTemplate template) {
        this.template = template;
        steps = template.getSteps().stream().map(t -> new PersonalStep(t, this)).collect(Collectors.toSet());
    }

    @Override
    public void publish() {
        super.publish();
        super.accept();
    }

    public void addContinuation(UserContinuationTemplate template) {
        if (this.getStatus() == Status.NEW) {
            PersonalContinuation continuation = new PersonalContinuation(getDoer(), template, this);
            continuations.add(continuation);
        } else {
            throw new IllegalStateException();
        }
    }
}
