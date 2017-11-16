package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.service.habit.HabitService;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.integration.domain.assemblers.HabitResAsm;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitResourceServiceImpl implements HabitResourceService {
    private final HabitService service;
    private final HabitResAsm habitAsm;

    public HabitResourceServiceImpl(HabitService service, HabitResAsm habitAsm) {
        this.service = service;
        this.habitAsm = habitAsm;
    }

    @Override
    public HabitResource create(Long userId, Habit habit) {
        return habitAsm.toResource(service.create(userId, habit));
    }

    @Override
    public HabitResource getHabit(Long userId, Long habitId) {
        return habitAsm.toResource(service.get(habitId));
    }
}
