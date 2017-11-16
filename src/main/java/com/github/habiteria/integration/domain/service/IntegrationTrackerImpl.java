package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.domain.service.habit.Tracker;
import com.github.habiteria.integration.domain.assemblers.ScheduledHabitResAsm;
import com.github.habiteria.integration.domain.resources.ScheduledHabitResource;
import com.github.habiteria.integration.domain.utils.ResourceUtils;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class IntegrationTrackerImpl implements IntegrationTracker {
    private final Tracker service;
    private final ScheduledHabitResAsm habitAsm;

    public IntegrationTrackerImpl(Tracker service,
                                  ScheduledHabitResAsm habitAsm) {
        this.service = service;
        this.habitAsm = habitAsm;
    }

    @Override
    public Resources<ScheduledHabitResource> getCurrentHabitList(Long userId) {
        Resources<ScheduledHabitResource> habits = ResourceUtils.toResources(service.getCurrentHabitList(userId), habitAsm);
        return new Resources<>(habits);
    }

    @Override
    public ScheduledHabitResource perform(Long habitId, int repeats) {
        ScheduledHabit habit = service.perform(habitId, repeats);
        return habitAsm.toResource(habit);
    }

    @Override
    public ScheduledHabitResource fail(Long habitId, int repeats) {
        ScheduledHabit habit = service.fail(habitId, repeats);
        return habitAsm.toResource(habit);
    }

    @Override
    public ScheduledHabitResource undo(Long habitId, int repeats) {
        return habitAsm.toResource(service.undo(habitId, repeats));
    }
}
