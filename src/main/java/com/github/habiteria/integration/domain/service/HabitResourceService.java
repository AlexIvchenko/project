package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.integration.domain.resources.HabitResource;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitResource create(Long userId, Habit habit);

    HabitResource getHabit(Long userId, Long habitId);
}
