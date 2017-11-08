package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.Habit;
import com.github.netcracker2017team.project.rest.controller.HabitsController;
import com.github.netcracker2017team.project.rest.resources.HabitResource;
import com.github.netcracker2017team.project.rest.resources.HabitsResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitsResourceAssembler implements ResourceAssembler<Set<Habit>, HabitsResource> {
    @Override
    public HabitsResource toResource(Set<Habit> entity) {
        return new HabitsResource(entity.stream().map(habit -> {
            UUID userId = UUID.fromString(habit.getOwner().getId());
            UUID habitId = UUID.fromString(habit.getId());
            HabitResource resource = new HabitResource(habit.getName(), habit.getDescription());
            resource.add(linkTo(methodOn(HabitsController.class).getHabit(userId, habitId))
                    .withSelfRel());
            return resource;
        }).collect(Collectors.toSet()));
    }
}
