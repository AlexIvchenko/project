package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.exceptions.client.FutureScheduleRetrievingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Generator {
    Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) throws FutureScheduleRetrievingException;

    CalendarRecord getOneRepeat(Habit habit, int repeat) throws FutureScheduleRetrievingException;

    Set<CalendarRecord> getOnlyVerifiableIn(Set<Habit> habits, LocalDateTime time) throws FutureScheduleRetrievingException;
}
