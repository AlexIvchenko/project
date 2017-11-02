package com.github.netcracker2017team.project.rest.processor;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.personal.PersonalGoal;
import com.github.netcracker2017team.project.rest.controller.UserGoalsController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
@Slf4j
public class PersonalGoalResourceProcessor implements ResourceProcessor<Resource<PersonalGoal>> {
    @Override
    public Resource<PersonalGoal> process(Resource<PersonalGoal> resource) {
        PersonalGoal goal = resource.getContent();
        UUID doerId = UUID.fromString(goal.getDoer().getId());
        UUID goalId = UUID.fromString(goal.getId());
        if (goal.getStatus() == Goal.Status.NEW) {
            resource.add(linkTo(methodOn(UserGoalsController.class).getContinuationTemplatesToAdd(doerId, goalId, null))
                    .withRel("getAvailableContinuationsToAdd"));
            resource.add(linkTo(methodOn(UserGoalsController.class).publishGoal(doerId, goalId, null))
                    .withRel("publish"));
        }
        return resource;
    }
}
