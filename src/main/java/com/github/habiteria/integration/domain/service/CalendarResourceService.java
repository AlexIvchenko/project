package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.CalendarResource;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
public interface CalendarResourceService {
    CalendarResource getCalendar(Long habitId, LocalDate from, LocalDate to);

    CalendarResource getCalendar(Long habitId);
}
