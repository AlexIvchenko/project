package com.github.netcracker2017team.project.domain.model.template;

import com.github.netcracker2017team.project.domain.model.Distributor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString
@Entity
@DiscriminatorValue("distributor")
public class DistributorGoalTemplate extends GoalTemplate {

    @ManyToOne
    @JoinColumn(name = "distributor_owner_id")
    private Distributor distributor;

    @Column(name = "points")
    private Integer points;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goal", orphanRemoval = true)
    private Set<DistributorStepTemplate> steps;

    public void addStep(DistributorStepTemplate stepTemplate) {
        if (steps == null) {
            steps = new HashSet<>();
        }
        stepTemplate.setGoal(this);
        steps.add(stepTemplate);
    }

    public void removeStep(DistributorStepTemplate stepTemplate) {
        if (steps == null) {
            steps = new HashSet<>();
        }
        if (stepTemplate.getGoal() == this) {
            stepTemplate.setGoal(null);
        }
        steps.remove(stepTemplate);
    }
}
