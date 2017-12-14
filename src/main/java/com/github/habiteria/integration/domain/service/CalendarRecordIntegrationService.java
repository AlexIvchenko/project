package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.CalendarRecordResource;

/**
 * @author Alex Ivchenko
 */
public interface CalendarRecordIntegrationService {
    CalendarRecordResource getRecord(Long habitId, int repeat);
}
