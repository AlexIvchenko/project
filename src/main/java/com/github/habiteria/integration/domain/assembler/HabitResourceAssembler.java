package com.github.habiteria.integration.domain.assembler;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitResourceAssembler implements ResourceAssembler<Habit, HabitResource> {
    @Override
    public HabitResource toResource(Habit entity) {
        HabitResource resource = new HabitResource(entity.getName(), entity.getDescription());
        UUID userId = UUID.fromString(entity.getOwner().getId());
        UUID habitId = UUID.fromString(entity.getId());
        resource.add(Links.getCalendarForLastMonth(userId, habitId));
        return resource;
    }
}
