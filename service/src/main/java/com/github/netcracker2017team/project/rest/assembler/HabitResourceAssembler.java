package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.Habit;
import com.github.netcracker2017team.project.domain.service.HabitService;
import com.github.netcracker2017team.project.rest.controller.HabitsController;
import com.github.netcracker2017team.project.rest.resources.HabitResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitResourceAssembler implements ResourceAssembler<Habit, HabitResource> {
    private final HabitService service;

    @Autowired
    public HabitResourceAssembler(HabitService service) {
        this.service = service;
    }

    @Override
    public HabitResource toResource(Habit entity) {
        final HabitResource resource = new HabitResource(entity.getName(), entity.getDescription());
        final UUID userId = UUID.fromString(entity.getOwner().getId());
        final UUID habitId = UUID.fromString(entity.getId());

        if (service.isAvailableToDoToday(entity)) {
            resource.add(linkTo(methodOn(HabitsController.class).perform(userId, habitId))
                    .withRel("perform"));
            resource.add(linkTo(methodOn(HabitsController.class).fail(userId, habitId))
                    .withRel("fail"));
        }
        return resource;
    }
}
