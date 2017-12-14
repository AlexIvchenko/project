package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import com.github.habiteria.integration.domain.service.CalendarRecordIntegrationService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Alex Ivchenko
 */
@Rest
public class CalendarRecordController {
    private final CalendarRecordIntegrationService service;

    public CalendarRecordController(CalendarRecordIntegrationService service) {
        this.service = service;
    }

    @GetMapping(path = "/users/{userId}/habits/{habitId}/repeats/{repeat}")
    public HttpEntity<CalendarRecordResource> getRecord(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId,
            @PathVariable("repeat") final Integer repeat) {
        return new HttpEntity<>(service.getRecord(habitId, repeat));
    }
}
