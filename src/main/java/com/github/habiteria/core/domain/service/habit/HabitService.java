package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.entities.Habit;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Habit create(UUID userId, Habit habit);

    Habit get(UUID habitId);
}
