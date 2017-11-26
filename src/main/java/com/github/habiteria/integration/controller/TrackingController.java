package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.controller.annotations.Rest;
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

    @PostMapping(path = "/users/{userId}/habits/tracking/{habitId}/{repeat}/perform")
    public HttpEntity<ScheduledHabitResource> perform(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId,
            @PathVariable("repeat") final int repeat) {
        return new HttpEntity<>(service.perform(habitId, repeat));
    }

    @PostMapping(path = "/users/{userId}/habits/tracking/{habitId}/{repeat}/fail")
    public HttpEntity<ScheduledHabitResource> fail(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId,
            @PathVariable("repeat") final int repeat) {
        return new HttpEntity<>(service.fail(habitId, repeat));
    }


    @PostMapping(path = "/users/{userId}/habits/tracking/{habitId}/{repeat}/undo")
    public HttpEntity<ScheduledHabitResource> undo(@PathVariable("userId") final Long userId,
                                                   @PathVariable("habitId") final Long habitId,
                                                   @PathVariable("repeat") final int repeat) {
        return new HttpEntity<>(service.undo(habitId, repeat));
    }
}
