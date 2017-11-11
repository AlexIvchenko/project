package com.github.habiteria.integration.domain.assembler;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitResourceAssembler implements ResourceAssembler<Habit, HabitResource> {
    @Override
    public HabitResource toResource(Habit entity) {
        return new HabitResource(entity.getName(), entity.getDescription());
    }
}
