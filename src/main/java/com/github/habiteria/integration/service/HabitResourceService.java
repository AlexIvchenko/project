package com.github.habiteria.integration.service;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitsResource getHabits(UUID userId, LocalDate date);

    HabitResource getHabit(UUID userId, UUID habitId, LocalDate date);

    HabitResource performHabit(UUID userId, UUID habitId, LocalDate date);

    HabitResource failHabit(UUID userId, UUID habitId, LocalDate date);

    HabitResource createHabit(UUID userId, Habit habit);

    HabitResource undoHabit(UUID userId, UUID habitId, LocalDate date);

    HabitsResource getUncheckedHabits(UUID userId);
}
