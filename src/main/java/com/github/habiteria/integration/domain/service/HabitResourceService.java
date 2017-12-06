package com.github.habiteria.integration.domain.service;

import com.github.habiteria.dto.HabitDto;
import com.github.habiteria.dto.PatchHabitDto;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.Resources;

/**
 * @author Alex Ivchenko
 */
public interface HabitResourceService {
    HabitResource create(Long userId, HabitDto habit);

    HabitResource getHabit(Long userId, Long habitId);

    Resources<HabitResource> getHabits(Long userId);

    HabitResource patch(Long userId, Long habitId, PatchHabitDto patch);
}
