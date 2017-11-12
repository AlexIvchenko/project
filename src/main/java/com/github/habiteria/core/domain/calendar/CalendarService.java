package com.github.habiteria.core.domain.calendar;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface CalendarService {
    Calendar getCalendar(UUID habitId, LocalDate from, LocalDate to);
}
