package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.integration.domain.resources.HabitResource;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitResource create(UUID userId, Habit habit);

    HabitResource getHabit(UUID userId, UUID habitId);
}
