package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.resources.CalendarResource;
import com.github.habiteria.integration.domain.service.CalendarResourceService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Alex Ivchenko
 */
@Rest
public class CalendarController {
    private final CalendarResourceService service;

    public CalendarController(CalendarResourceService service) {
        this.service = service;
    }

    @GetMapping("/users/{userId}/habits/{habitId}/calendar/")
    public HttpEntity<CalendarResource> getCalendar(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId) {
        CalendarResource resource = service.getCalendar(habitId);
        return new HttpEntity<>(resource);
    }
}
