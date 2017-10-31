package com.github.netcracker2017team.project.domain.model.template;

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
@ToString(callSuper = true, exclude = "goal")
@Entity
@DiscriminatorValue("distributor")
public class DistributorStepTemplate extends StepTemplate {
    @ManyToOne
    @JoinColumn(name = "distributor_goal_template_id")
    private DistributorGoalTemplate goal;
}
