package com.github.habiteria.integration.domain.assemblers;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitResAsm implements ResourceAssembler<Habit, HabitResource> {
    @Override
    public HabitResource toResource(Habit entity) {
        HabitResource resource = new HabitResource(entity.getName(), entity.getDescription());
        Long userId = entity.getOwner().getId();
        Long habitId = entity.getId();
        resource.add(Links.getCalendarForLastMonth(userId, habitId));
        return resource;
    }
}
