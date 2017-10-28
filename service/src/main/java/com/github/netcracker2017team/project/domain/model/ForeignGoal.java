package com.github.netcracker2017team.project.domain.model;

import com.github.netcracker2017team.project.domain.model.template.DistributorGoalTemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@DiscriminatorValue("foreign")
public class ForeignGoal extends Goal {

    @ManyToOne
    @JoinColumn(name = "distributor_goal_template_id")
    private DistributorGoalTemplate template;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ForeignStep> steps;
}

