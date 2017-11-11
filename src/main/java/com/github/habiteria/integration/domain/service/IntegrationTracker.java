package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.ScheduledHabitResource;
import org.springframework.hateoas.Resources;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface IntegrationTracker {
    Resources<ScheduledHabitResource> getCurrentHabitList(UUID userId);

    ScheduledHabitResource perform(UUID habitId, int repeat);

    ScheduledHabitResource fail(UUID habitId, int repeat);

    ScheduledHabitResource undo(UUID habitId, int repeat);
}
