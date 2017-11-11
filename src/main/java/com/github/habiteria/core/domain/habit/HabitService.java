package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.Habit;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Habit create(UUID userId, Habit habit);
}
