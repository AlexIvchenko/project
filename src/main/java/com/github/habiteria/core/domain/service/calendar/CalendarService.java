package com.github.habiteria.core.domain.service.calendar;

import com.github.habiteria.core.domain.model.Calendar;
import com.github.habiteria.core.exceptions.client.FutureScheduleRetrievingException;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public interface CalendarService {
    Calendar getCalendar(Long habitId, LocalDate from, LocalDate to) throws FutureScheduleRetrievingException;

    Calendar getCalendar(Long habitId);
}
