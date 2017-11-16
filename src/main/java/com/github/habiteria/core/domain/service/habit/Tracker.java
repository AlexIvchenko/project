package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.model.ScheduledHabit;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface Tracker {
    Set<ScheduledHabit> getCurrentHabitList(UUID userId);

    ScheduledHabit perform(UUID habitId, int repeat);

    ScheduledHabit fail(UUID habitId, int repeat);

    ScheduledHabit undo(UUID habitId, int repeat);
}
