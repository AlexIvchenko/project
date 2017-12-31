package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import com.github.habiteria.integration.domain.resources.ScheduledHabitResource;
import com.github.habiteria.integration.domain.service.IntegrationTracker;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Alex Ivchenko
 */
@Rest
public class TrackingController {
    private final IntegrationTracker service;

    public TrackingController(IntegrationTracker service) {
        this.service = service;
    }

    @GetMapping(path = "/users/{userId}/habits/tracking")
    public HttpEntity<Resources<ScheduledHabitResource>> getCurrentHabitList(
            @PathVariable("userId") final Long userId) {
        return new HttpEntity<>(service.getCurrentHabitList(userId));
    }

    @GetMapping(path = "/users/{userId}/habits/{habitId}/tracking")
    public HttpEntity<Resources<CalendarRecordResource>> getHabitTracking(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId) {
        return new HttpEntity<>(service.getHabitTracking(habitId));
    }

    @PostMapping(path = "/users/{userId}/habits/tracking/{habitId}/repeats/{repeat}/perform")
    public HttpEntity<CalendarRecordResource> perform(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId,
            @PathVariable("repeat") final int repeat) {
        return new HttpEntity<>(service.perform(habitId, repeat));
    }

    @PostMapping(path = "/users/{userId}/habits/tracking/{habitId}/repeats/{repeat}/fail")
    public HttpEntity<CalendarRecordResource> fail(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId,
            @PathVariable("repeat") final int repeat) {
        return new HttpEntity<>(service.fail(habitId, repeat));
    }

    @PostMapping(path = "/users/{userId}/habits/tracking/{habitId}/repeats/{repeat}/undo")
    public HttpEntity<CalendarRecordResource> undo(@PathVariable("userId") final Long userId,
                                                   @PathVariable("habitId") final Long habitId,
                                                   @PathVariable("repeat") final int repeat) {
        return new HttpEntity<>(service.undo(habitId, repeat));
    }
}
