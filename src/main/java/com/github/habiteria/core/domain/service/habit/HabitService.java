package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.entities.Habit;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Habit create(Long userId, Habit habit);

    Habit get(Long habitId);
}
