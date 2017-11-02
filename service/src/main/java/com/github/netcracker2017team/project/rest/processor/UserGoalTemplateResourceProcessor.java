package com.github.netcracker2017team.project.rest.processor;

import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
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
@Slf4j
@Component
public class UserGoalTemplateResourceProcessor implements ResourceProcessor<Resource<UserGoalTemplate>>{
    @Override
    public Resource<UserGoalTemplate> process(Resource<UserGoalTemplate> resource) {
        UserGoalTemplate goalTemplate = resource.getContent();
        UUID userId = UUID.fromString(goalTemplate.getOwner().getId());
        UUID goalId = UUID.fromString(goalTemplate.getId());
        resource.add(linkTo(methodOn(UserGoalsController.class).applyUserToHisGoal(userId, goalId, null)).withRel("apply"));
        return resource;
    }
}
