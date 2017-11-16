package com.github.habiteria.integration.controller;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.resources.HabitResource;
import com.github.habiteria.integration.domain.service.HabitResourceService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Alex Ivchenko
 */
@Rest
public class HabitController {
    private final HabitResourceService service;

    public HabitController(HabitResourceService service) {
        this.service = service;
    }

    @PostMapping(path = "/users/{userId}/habits")
    public HttpEntity<HabitResource> create(
            @PathVariable("userId") final Long userId,
            @RequestBody final Habit habit) {
        return new HttpEntity<>(service.create(userId, habit));
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/card")
    public HttpEntity<HabitResource> getHabitCard(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId) {
        return new HttpEntity<>(service.getHabit(userId, habitId));
    }
}
