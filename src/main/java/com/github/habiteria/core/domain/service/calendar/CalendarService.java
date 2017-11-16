package com.github.habiteria.core.domain.service.calendar;

import com.github.habiteria.core.domain.model.Calendar;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface CalendarService {
    Calendar getCalendar(UUID habitId, LocalDate from, LocalDate to);
}
