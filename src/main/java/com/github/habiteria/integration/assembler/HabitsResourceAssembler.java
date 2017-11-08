package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.integration.resources.HabitsResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

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
        return new HabitsResource(entity.stream().map(habitAsm::toResource)
                .collect(Collectors.toSet()));
    }
}
