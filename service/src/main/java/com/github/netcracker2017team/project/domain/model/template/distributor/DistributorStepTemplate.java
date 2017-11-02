package com.github.netcracker2017team.project.domain.model.template.distributor;

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
@DiscriminatorValue("distributor")
public class DistributorStepTemplate extends StepTemplate {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_goal_template_id")
    private DistributorGoalTemplate goal;
}
