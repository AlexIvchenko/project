package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.User;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Scheduler {
    CalendarRecord getRecord(Habit habit, int repeat);

    CalendarRecord update(CalendarRecord record);

    Set<CalendarRecord> findVerifiable(User user);
}
