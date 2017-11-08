package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.Habit;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Set<Habit> getDailyHabits(UUID userId);

    Habit getDailyHabit(UUID userId, UUID habitId);

    Habit perform(UUID userId, UUID habitId);

    Habit fail(UUID userId, UUID habitId);

    Habit createDailyHabit(UUID userId, Habit habit);

//    boolean isAvailableToDoToday(UUID userId, UUID habitId);

    boolean isAvailableToDoToday(Habit habit);
}
