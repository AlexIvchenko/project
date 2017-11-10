package com.github.habiteria.domain.service.habit.tracking;

import com.github.habiteria.domain.model.Habit;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * This service contains logic to build current HabitSnapshot.
 * This means that this service should check habit, results, schedule and endingCondition
 * to represent current habit snapshot in define date.
 * We need to separate logic for working with different types of schedules or endingConditions
 * into modules.
 * @author Alex Ivchenko
 */
public interface HabitTrackingService {
    HabitSnapshot createHabit(UUID userId, Habit habit);

    HabitSnapshot getHabit(UUID userId, UUID habitId, LocalDate date);

    Set<HabitSnapshot> getHabits(UUID userId, LocalDate date);

    HabitSnapshot performHabit(UUID userId, UUID habitId, LocalDate date);

    HabitSnapshot failHabit(UUID userId, UUID habitId, LocalDate date);

    HabitSnapshot undoHabit(UUID userId, UUID habitId, LocalDate date);
}
