package com.github.netcracker2017team.project.rest.service;

import com.github.netcracker2017team.project.domain.model.Habit;
import com.github.netcracker2017team.project.rest.resources.HabitResource;
import com.github.netcracker2017team.project.rest.resources.HabitsResource;

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
