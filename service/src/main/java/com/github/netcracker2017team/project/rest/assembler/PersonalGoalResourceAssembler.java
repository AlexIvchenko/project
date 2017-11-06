package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.personal.PersonalGoal;
import com.github.netcracker2017team.project.rest.controller.UserGoalsController;
import com.github.netcracker2017team.project.rest.resources.PersonalGoalResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class PersonalGoalResourceAssembler implements ResourceAssembler<PersonalGoal, PersonalGoalResource> {
    private final PersonalStepResourceAssembler stepAsm;

    public PersonalGoalResourceAssembler(PersonalStepResourceAssembler stepAsm) {
        this.stepAsm = stepAsm;
    }

    public PersonalGoalResource toResource(PersonalGoal entity) {
        final String name = entity.getTemplate().getName();
        final String description = entity.getTemplate().getDescription();
        final PersonalGoalResource resource = new PersonalGoalResource(name, description);
        final UUID doerId = UUID.fromString(entity.getDoer().getId());
        final UUID goalId = UUID.fromString(entity.getId());

        resource.add(linkTo(methodOn(UserGoalsController.class).getSteps(doerId, goalId))
                .withRel("steps"));
//        resource.add(linkTo(methodOn(UserGoalsController.class).getContinuations(doerId, goalId))
//                  .withRel("continuations"));

        if (entity.getStatus() == Goal.Status.NEW) {
            resource.add(linkTo(methodOn(UserGoalsController.class).getContinuationTemplatesToAdd(doerId, goalId))
                    .withRel("getAvailableContinuationsToAdd"));
            resource.add(linkTo(methodOn(UserGoalsController.class).acceptGoal(doerId, goalId))
                    .withRel("accept"));
        }
        if (entity.getStatus() == Goal.Status.ACCEPTED) {
            resource.add(linkTo(methodOn(UserGoalsController.class).resolveSuccess(doerId, goalId))
                    .withRel("resolveSuccess"));
            resource.add(linkTo(methodOn(UserGoalsController.class).resolveFail(doerId, goalId))
                    .withRel("resolveFail"));
        }
        return resource;
    }
}
