package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.integration.controller.HabitsController;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;
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
    private final HabitResourceAssembler habitAsm;

    public HabitsResourceAssembler(HabitResourceAssembler habitAsm) {
        this.habitAsm = habitAsm;
    }

    @Override
    public HabitsResource toResource(Set<Habit> entity) {
        return new HabitsResource(entity.stream().map(habit -> {
            UUID userId = UUID.fromString(habit.getOwner().getId());
            UUID habitId = UUID.fromString(habit.getId());
            HabitResource resource = habitAsm.toResource(habit);
            resource.add(linkTo(methodOn(HabitsController.class).getHabit(userId, habitId))
                    .withSelfRel());
            return resource;
        }).collect(Collectors.toSet()));
    }
}
