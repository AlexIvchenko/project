package com.github.habiteria.domain.service.habit.core;

import com.github.habiteria.domain.model.Habit;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitSnapshotService {
    HabitSnapshot createHabit(UUID userId, Habit habit);

    HabitSnapshot getHabit(UUID userId, UUID habitId, LocalDate date);

    Set<HabitSnapshot> getHabits(UUID userId, LocalDate date);

    HabitSnapshot performHabit(UUID userId, UUID habitId, LocalDate date);

    HabitSnapshot failHabit(UUID userId, UUID habitId, LocalDate date);

    HabitSnapshot undoHabit(UUID userId, UUID habitId, LocalDate date);
}
