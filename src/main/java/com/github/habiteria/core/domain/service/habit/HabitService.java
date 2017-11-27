package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.exceptions.client.ResourceNotFoundException;
import com.github.habiteria.dto.HabitDto;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface HabitService {
    Habit create(Long userId, HabitDto dto);

    Habit get(Long habitId) throws ResourceNotFoundException;

    Set<Habit> getHabits(Long userId);
}
