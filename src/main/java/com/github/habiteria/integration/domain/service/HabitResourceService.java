package com.github.habiteria.integration.domain.service;

import com.github.habiteria.dto.HabitDto;
import com.github.habiteria.integration.domain.resources.HabitResource;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitResource create(Long userId, HabitDto habit);

    HabitResource getHabit(Long userId, Long habitId);
}
