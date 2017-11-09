package com.github.habiteria.integration.service;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.service.HabitSnapshot;
import com.github.habiteria.domain.service.HabitSnapshotService;
import com.github.habiteria.integration.assembler.HabitSnapshotResourceAssembler;
import com.github.habiteria.integration.assembler.HabitSnapshotsResourceAssembler;
import com.github.habiteria.integration.links.Links;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitResourceServiceImpl implements HabitResourceService {
    private final HabitSnapshotService service;
    private final HabitSnapshotResourceAssembler habitAsm;
    private final HabitSnapshotsResourceAssembler habitsAsm;

    public HabitResourceServiceImpl(@Qualifier("habitSnapshotService") HabitSnapshotService service,
                                    HabitSnapshotResourceAssembler habitAsm,
                                    HabitSnapshotsResourceAssembler habitsAsm) {
        this.service = service;
        this.habitAsm = habitAsm;
        this.habitsAsm = habitsAsm;
    }

    @Override
    public HabitsResource getHabits(UUID userId, LocalDate date) {
        Set<HabitSnapshot> habits = service.getHabits(userId, date);
        HabitsResource resource = habitsAsm.toResource(habits);
        resource.add(Links.createHabit(userId));
        return resource;
    }

    @Override
    public HabitResource getHabit(UUID userId, UUID habitId, LocalDate date) {
        HabitSnapshot habit = service.getHabit(userId, habitId, date);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource performHabit(UUID userId, UUID habitId, LocalDate date) {
        HabitSnapshot habit = service.performHabit(userId, habitId, date);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource failHabit(UUID userId, UUID habitId, LocalDate date) {
        HabitSnapshot habit = service.failHabit(userId, habitId, date);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource createHabit(UUID userId, Habit habit) {
        HabitSnapshot snapshot = service.createHabit(userId, habit);
        return habitAsm.toResource(snapshot);
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        service.failUncheckedHabits(userId);
    }

    @Override
    public HabitsResource getUncheckedHabits(UUID userId) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Set<HabitResource> resources = service.getHabits(userId, yesterday)
                .stream()
                .filter(HabitSnapshot::isUnchecked)
                .map(habitAsm::toResource)
                .collect(Collectors.toSet());
        return new HabitsResource(resources);
    }

    @Override
    public HabitResource undoHabit(UUID userId, UUID habitId, LocalDate date) {
        return habitAsm.toResource(service.undoHabit(userId, habitId, date));
    }
}
