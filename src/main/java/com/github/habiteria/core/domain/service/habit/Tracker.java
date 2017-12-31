package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.model.ScheduledHabit;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.exceptions.client.actions.IllegalCalendarRecordStateTransitionException;
import com.github.habiteria.exceptions.client.ResourceNotFoundException;
import com.github.habiteria.exceptions.client.TryToVerifyNotVerifiableHabitException;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Tracker {
    Set<ScheduledHabit> getCurrentHabitList(Long userId)
            throws ResourceNotFoundException;

    Set<CalendarRecord> getHabitTracking(Long habitId)
            throws ResourceNotFoundException;

    CalendarRecord perform(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException;

    CalendarRecord fail(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException;

    CalendarRecord undo(Long habitId, int repeat) throws ResourceNotFoundException,
            TryToVerifyNotVerifiableHabitException, IllegalCalendarRecordStateTransitionException;
}
