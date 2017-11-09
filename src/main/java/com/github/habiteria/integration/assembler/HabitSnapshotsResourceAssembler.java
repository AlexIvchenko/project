package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.service.habit.core.HabitSnapshot;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitSnapshotsResourceAssembler implements ResourceAssembler<Set<HabitSnapshot>, HabitsResource> {
    private final HabitSnapshotResourceAssembler habitAsm;

    public HabitSnapshotsResourceAssembler(HabitSnapshotResourceAssembler habitAsm) {
        this.habitAsm = habitAsm;
    }

    @Override
    public HabitsResource toResource(Set<HabitSnapshot> entity) {
        Set<HabitResource> resources = entity.stream()
                .map(habitAsm::toResource)
                .collect(Collectors.toSet());
        return new HabitsResource(resources);
    }
}
