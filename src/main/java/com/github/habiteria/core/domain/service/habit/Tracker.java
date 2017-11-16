package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.model.ScheduledHabit;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Tracker {
    Set<ScheduledHabit> getCurrentHabitList(Long userId);

    ScheduledHabit perform(Long habitId, int repeat);

    ScheduledHabit fail(Long habitId, int repeat);

    ScheduledHabit undo(Long habitId, int repeat);
}
