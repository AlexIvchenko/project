package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.service.HabitSnapshot;
import com.github.habiteria.integration.controller.HabitsController;
import com.github.habiteria.integration.links.Links;
import com.github.habiteria.integration.resources.HabitResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitSnapshotResourceAssembler implements ResourceAssembler<HabitSnapshot, HabitResource> {
    @Override
    public HabitResource toResource(HabitSnapshot entity) {
        Habit habit = entity.getHabit();
        final HabitResource resource = new HabitResource(habit.getName(), habit.getDescription(), entity.isAvailable(), entity.isRequired(), entity.getStatus());
        final UUID userId = UUID.fromString(habit.getOwner().getId());
        final UUID habitId = UUID.fromString(habit.getId());

        resource.add(linkTo(methodOn(HabitsController.class).getHabit(userId, habitId, entity.getDate()))
                .withSelfRel());

        if (entity.isAvailable()) {
            LocalDate today = LocalDate.now();
            LocalDate yesterday = today.minusDays(1);
            if (entity.getStatus() == HabitSnapshot.Status.UNDEFINED) {
                if (entity.getDate().equals(today)) {
                    resource.add(Links.perform(userId, habitId));
                    resource.add(Links.fail(userId, habitId));
                }
                if (entity.getDate().equals(yesterday)) {
                    resource.add(Links.performYesterday(userId, habitId));
                    resource.add(Links.failYesterday(userId, habitId));
                }
            } else {
                if (entity.getDate().equals(today)) {
                    resource.add(Links.undo(userId, habitId));
                }
            }
        }
        return resource;
    }
}
