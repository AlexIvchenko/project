package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
import com.github.netcracker2017team.project.rest.controller.UserGoalsController;
import com.github.netcracker2017team.project.rest.controller.UserStepTemplatesController;
import com.github.netcracker2017team.project.rest.resources.UserGoalTemplateResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.github.netcracker2017team.project.rest.utils.ResourceUtils.toResourceSet;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class UserGoalTemplateResourceAssembler implements ResourceAssembler<UserGoalTemplate, UserGoalTemplateResource> {
    private final UserStepTemplateResourceAssembler stepAsm;

    public UserGoalTemplateResourceAssembler(UserStepTemplateResourceAssembler stepAsm) {
        this.stepAsm = stepAsm;
    }

    @Override
    public UserGoalTemplateResource toResource(UserGoalTemplate entity) {
        toResourceSet(entity.getSteps(), stepAsm);
        UserGoalTemplateResource resource = new UserGoalTemplateResource(entity.getName(), entity.getDescription());
        UUID userId = UUID.fromString(entity.getOwner().getId());
        UUID goalId = UUID.fromString(entity.getId());
        resource.add(linkTo(methodOn(UserGoalsController.class).applyUserToHisGoal(userId, goalId))
                .withRel("apply"));
        resource.add(linkTo(methodOn(UserStepTemplatesController.class).getStepTemplates(userId, goalId))
                .withRel("steps"));
        return resource;
    }
}
