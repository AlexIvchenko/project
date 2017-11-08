package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.rest.controller.HabitsController;
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
        resource.add(linkTo(methodOn(HabitsController.class).createHabit(id, null))
                .withRel("createHabit"));
        resource.add(linkTo(methodOn(HabitsController.class).getHabits(id))
                .withRel("getHabits"));
        return resource;
    }
}
