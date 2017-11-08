package com.github.netcracker2017team.project.rest.service;

import com.github.netcracker2017team.project.domain.model.Habit;
import com.github.netcracker2017team.project.domain.service.HabitService;
import com.github.netcracker2017team.project.rest.assembler.HabitResourceAssembler;
import com.github.netcracker2017team.project.rest.assembler.HabitsResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.HabitResource;
import com.github.netcracker2017team.project.rest.resources.HabitsResource;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitResourceServiceImpl implements HabitResourceService {
    private final HabitService service;
    private final HabitResourceAssembler habitAsm;
    private final HabitsResourceAssembler habitsAsm;

    public HabitResourceServiceImpl(HabitService service,
                                    HabitResourceAssembler habitAsm,
                                    HabitsResourceAssembler habitsAsm) {
        this.service = service;
        this.habitAsm = habitAsm;
        this.habitsAsm = habitsAsm;
    }

    @Override
    public HabitsResource getDailyHabits(UUID userId) {
        Set<Habit> habits = service.getDailyHabits(userId);
        return habitsAsm.toResource(habits);
    }

    @Override
    public HabitResource getDailyHabit(UUID userId, UUID habitId) {
        Habit habit = service.getDailyHabit(userId, habitId);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource perform(UUID userId, UUID habitId) {
        Habit habit = service.perform(userId, habitId);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource fail(UUID userId, UUID habitId) {
        Habit habit = service.fail(userId, habitId);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource createHabit(UUID userId, Habit habit) {
        habit = service.createDailyHabit(userId, habit);
        return habitAsm.toResource(habit);
    }
}
