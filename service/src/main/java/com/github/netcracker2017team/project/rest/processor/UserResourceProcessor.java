package com.github.netcracker2017team.project.rest.processor;

import com.github.netcracker2017team.project.domain.model.User;
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
public class UserResourceProcessor implements ResourceProcessor<Resource<User>> {
    @Override
    public Resource<User> process(Resource<User> resource) {
        log.info("process " + resource);
        UUID id = UUID.fromString(resource.getContent().getId());
        resource.add(linkTo(methodOn(TemplatesController.class).createGoalTemplate(id, null, null)).withRel("createGoalTemplate"));
        resource.add(linkTo(methodOn(TemplatesController.class).getGoalTemplates(id, null)).withRel("getGoalTemplates"));
        resource.add(linkTo(methodOn(TemplatesController.class).createContinuationTemplate(id, null, null)).withRel("createContinuationTemplate"));
        return resource;
    }
}
