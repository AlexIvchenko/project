package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.domain.service.habit.Tracker;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.integration.domain.assemblers.CalendarRecordResAsm;
import com.github.habiteria.integration.domain.assemblers.ScheduledHabitResAsm;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
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
    private final CalendarRecordResAsm recAsm;

    public IntegrationTrackerImpl(Tracker service,
                                  ScheduledHabitResAsm habitAsm, CalendarRecordResAsm recAsm) {
        this.service = service;
        this.habitAsm = habitAsm;
        this.recAsm = recAsm;
    }

    @Override
    public Resources<CalendarRecordResource> getHabitTracking(Long habitId) {
        return ResourceUtils.toResources(service.getHabitTracking(habitId), recAsm);
    }

    @Override
    public Resources<ScheduledHabitResource> getCurrentHabitList(Long userId) {
        return ResourceUtils.toResources(service.getCurrentHabitList(userId), habitAsm);
    }

    @Override
    public CalendarRecordResource perform(Long habitId, int repeats) {
        CalendarRecord record = service.perform(habitId, repeats);
        return recAsm.toResource(record);
    }

    @Override
    public CalendarRecordResource fail(Long habitId, int repeats) {
        CalendarRecord record = service.fail(habitId, repeats);
        return recAsm.toResource(record);
    }

    @Override
    public CalendarRecordResource undo(Long habitId, int repeats) {
        CalendarRecord record = service.undo(habitId, repeats);
        return recAsm.toResource(record);
    }
}
