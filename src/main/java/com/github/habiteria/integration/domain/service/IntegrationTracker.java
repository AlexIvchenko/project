package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.ScheduledHabitResource;
import org.springframework.hateoas.Resources;


/**
 * @author Alex Ivchenko
 */
public interface IntegrationTracker {
    Resources<ScheduledHabitResource> getCurrentHabitList(Long userId);

    ScheduledHabitResource perform(Long habitId, int repeat);

    ScheduledHabitResource fail(Long habitId, int repeat);

    ScheduledHabitResource undo(Long habitId, int repeat);
}
