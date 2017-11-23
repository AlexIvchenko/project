package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Generator {
    Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to);

    CalendarRecord getOneRepeat(Habit habit, int repeat);

    Set<CalendarRecord> getOnlyVerifiableIn(User user, LocalDateTime time);

    default Set<CalendarRecord> getOnlyVerifiableNow(User user) {
        return getOnlyVerifiableIn(user, LocalDateTime.now());
    }
}
