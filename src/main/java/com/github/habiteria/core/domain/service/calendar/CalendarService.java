package com.github.habiteria.core.domain.service.calendar;

import com.github.habiteria.core.domain.model.Calendar;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public interface CalendarService {
    Calendar getCalendar(Long habitId, LocalDate from, LocalDate to);
}
