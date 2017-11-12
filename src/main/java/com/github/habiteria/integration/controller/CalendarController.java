package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.domain.resources.CalendarResource;
import com.github.habiteria.integration.domain.service.CalendarResourceService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Rest
public class CalendarController {
    private final CalendarResourceService service;

    public CalendarController(CalendarResourceService service) {
        this.service = service;
    }

    @GetMapping("/users/{userId}/habits/{habitId}/card/calendar/")
    public HttpEntity<CalendarResource> getCalendar(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId,
            @RequestParam("from") @Date final LocalDate from,
            @RequestParam("to") @Date final LocalDate to) {
        CalendarResource resource = service.getCalendar(habitId, from, to);
        return new HttpEntity<>(resource);
    }
}
