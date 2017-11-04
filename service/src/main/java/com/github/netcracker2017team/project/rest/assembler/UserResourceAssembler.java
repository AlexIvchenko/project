package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.rest.controller.UserContinuationTemplatesController;
import com.github.netcracker2017team.project.rest.controller.UserGoalsController;
import com.github.netcracker2017team.project.rest.controller.UserGoalTemplatesController;
import com.github.netcracker2017team.project.rest.resources.UserResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource> {
    @Override
    public UserResource toResource(User entity) {
        UserResource resource = new UserResource(entity.getUsername(), entity.getFirstName(), entity.getLastName());
        UUID id = UUID.fromString(entity.getId());
        resource.add(linkTo(methodOn(UserGoalTemplatesController.class).createGoalTemplate(id, null))
                .withRel("createGoalTemplate"));
        resource.add(linkTo(methodOn(UserContinuationTemplatesController.class).createContinuationTemplate(id, null))
                .withRel("createContinuationTemplate"));
        resource.add(linkTo(methodOn(UserContinuationTemplatesController.class).getContinuationTemplates(id))
                .withRel("getContinuationTemplates"));
        resource.add(linkTo(methodOn(UserGoalTemplatesController.class).getGoalTemplates(id))
                .withRel("getGoalTemplates"));
        resource.add(linkTo(methodOn(UserGoalsController.class).getNewGoals(id))
                .withRel("getNewGoals"));
        resource.add(linkTo(methodOn(UserGoalsController.class).getPublishedGoals(id))
                .withRel("getPublishedGoals"));
        return resource;
    }
}
