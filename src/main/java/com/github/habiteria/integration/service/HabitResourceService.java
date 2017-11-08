package com.github.habiteria.integration.service;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitsResource getHabits(UUID userId);

    HabitResource getHabit(UUID userId, UUID habitId);

    HabitResource perform(UUID userId, UUID habitId);

    HabitResource fail(UUID userId, UUID habitId);

    HabitResource createHabit(UUID userId, Habit habit);
}
