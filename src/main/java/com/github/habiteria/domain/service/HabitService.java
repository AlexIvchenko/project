package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.Habit;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Habit createHabit(UUID userId, Habit habit);
}
