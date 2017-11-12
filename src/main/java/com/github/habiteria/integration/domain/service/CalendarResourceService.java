package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.CalendarResource;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface CalendarResourceService {
    CalendarResource getCalendar(UUID habitId, LocalDate from, LocalDate to);
}
