package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.exceptions.client.FutureScheduleRetrievingException;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Scheduler {
    CalendarRecord getRecord(Habit habit, int repeat) throws FutureScheduleRetrievingException;

    CalendarRecord update(CalendarRecord record);

    Set<CalendarRecord> findVerifiable(User user);

    Set<CalendarRecord> getRecords(Habit habit, LocalDate from, LocalDate to) throws FutureScheduleRetrievingException;
}
