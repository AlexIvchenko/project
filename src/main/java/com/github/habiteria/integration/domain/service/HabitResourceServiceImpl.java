package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.service.habit.HabitService;
import com.github.habiteria.dto.HabitDto;
import com.github.habiteria.integration.domain.assemblers.HabitResAsm;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import static com.github.habiteria.integration.domain.utils.ResourceUtils.toResources;

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
    public HabitResource create(Long userId, HabitDto habit) {
        return habitAsm.toResource(service.create(userId, habit));
    }

    @Override
    public HabitResource getHabit(Long userId, Long habitId) {
        return habitAsm.toResource(service.get(habitId));
    }

    @Override
    public Resources<HabitResource> getHabits(Long userId) {
        return toResources(service.getHabits(userId), habitAsm);
    }
}
