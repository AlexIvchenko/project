package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.User;
import com.github.habiteria.integration.controller.HabitsController;
import com.github.habiteria.integration.resources.UserResource;
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
