package com.github.habitaria.integration.service;

import com.github.habitaria.domain.model.Habit;
import com.github.habitaria.integration.resources.HabitResource;
import com.github.habitaria.integration.resources.HabitsResource;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitsResource getDailyHabits(UUID userId);

    HabitResource getDailyHabit(UUID userId, UUID habitId);

    HabitResource perform(UUID userId, UUID habitId);

    HabitResource fail(UUID userId, UUID habitId);

    HabitResource createHabit(UUID userId, Habit habit);
}