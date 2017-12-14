package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import com.github.habiteria.integration.domain.resources.ScheduledHabitResource;
import org.springframework.hateoas.Resources;


/**
 * @author Alex Ivchenko
 */
public interface IntegrationTracker {
    Resources<CalendarRecordResource> getHabitTracking(Long habitId);

    Resources<ScheduledHabitResource> getCurrentHabitList(Long userId);

    CalendarRecordResource perform(Long habitId, int repeat);

    CalendarRecordResource fail(Long habitId, int repeat);

    CalendarRecordResource undo(Long habitId, int repeat);
}
