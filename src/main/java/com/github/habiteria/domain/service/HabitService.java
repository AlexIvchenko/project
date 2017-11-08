package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.Habit;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Set<Habit> getHabits(UUID userId);

    Habit getHabit(UUID userId, UUID habitId);

    Habit perform(UUID userId, UUID habitId);

    Habit fail(UUID userId, UUID habitId);

    Habit createHabit(UUID userId, Habit habit);

    boolean isAvailableToDoToday(Habit habit);
}
