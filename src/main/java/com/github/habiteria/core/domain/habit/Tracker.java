package com.github.habiteria.core.domain.habit;

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
