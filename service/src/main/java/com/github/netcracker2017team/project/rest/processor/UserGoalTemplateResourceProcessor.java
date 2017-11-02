package com.github.netcracker2017team.project.rest.processor;

import com.github.netcracker2017team.project.domain.model.template.UserGoalTemplate;
import com.github.netcracker2017team.project.rest.controller.TemplatesController;
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
public class UserGoalTemplateResourceProcessor implements ResourceProcessor<Resource<UserGoalTemplate>> {
    @Override
    public Resource<UserGoalTemplate> process(Resource<UserGoalTemplate> resource) {
        UUID ownerId = UUID.fromString(resource.getContent().getOwner().getId());
        resource.add(linkTo(methodOn(TemplatesController.class).getContinuationTemplates(ownerId, null)).withRel("getAvailableContinuations"));
        return resource;
    }
}
