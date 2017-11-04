package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.template.user.UserStepTemplate;
import com.github.netcracker2017team.project.rest.controller.UserGoalTemplatesController;
import com.github.netcracker2017team.project.rest.resources.UserStepTemplateResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class UserStepTemplateResourceAssembler implements ResourceAssembler<UserStepTemplate, UserStepTemplateResource> {
    @Override
    public UserStepTemplateResource toResource(UserStepTemplate entity) {
        UserStepTemplateResource resource = new UserStepTemplateResource(entity.getName(), entity.getDescription());
        UUID userId = UUID.fromString(entity.getGoal().getOwner().getId());
        UUID goalId = UUID.fromString(entity.getGoal().getId());
        resource.add(linkTo(methodOn(UserGoalTemplatesController.class).getGoalTemplate(userId, goalId))
                .withRel("goal"));
        return resource;
    }
}
