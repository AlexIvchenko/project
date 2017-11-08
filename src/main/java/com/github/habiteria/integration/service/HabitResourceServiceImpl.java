package com.github.habiteria.integration.service;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.service.HabitService;
import com.github.habiteria.integration.assembler.HabitResourceAssembler;
import com.github.habiteria.integration.assembler.HabitsResourceAssembler;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;
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
