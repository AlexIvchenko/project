package com.github.habiteria.integration.domain.assembler;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.ScheduledHabitResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Component
public class ScheduledHabitResourceAssembler implements ResourceAssembler<ScheduledHabit, ScheduledHabitResource> {
    @Override
    public ScheduledHabitResource toResource(ScheduledHabit entity) {
        Habit habit = entity.getHabit();
        final ScheduledHabitResource resource = new ScheduledHabitResource(habit.getName(), habit.getDescription(), entity.isVerifiable(), entity.isRequired(), entity.getStatus());
        final UUID userId = UUID.fromString(habit.getOwner().getId());
        final UUID habitId = UUID.fromString(habit.getId());

        if (entity.isVerifiable()) {
            if (entity.isUnverified()) {
                resource.add(Links.perform(userId, habitId, entity.getRepeat()));
                resource.add(Links.fail(userId, habitId, entity.getRepeat()));
            } else {
                resource.add(Links.undo(userId, habitId, entity.getRepeat()));
            }
        }

        resource.add(Links.getHabitCard(userId, habitId));

        return resource;
    }
}
