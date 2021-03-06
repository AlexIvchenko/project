package com.github.habiteria.integration.controller;

import com.github.habiteria.dto.HabitDto;
import com.github.habiteria.dto.PatchHabitDto;
import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.resources.HabitResource;
import com.github.habiteria.integration.domain.service.HabitResourceService;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Ivchenko
 */
@Rest
@CrossOrigin
public class HabitController {
    private final HabitResourceService service;

    public HabitController(HabitResourceService service) {
        this.service = service;
    }

    @PostMapping(path = "/users/{userId}/habits")
    public HttpEntity<HabitResource> create(
            @PathVariable("userId") final Long userId,
            @RequestBody final HabitDto habit) {
        return new HttpEntity<>(service.create(userId, habit));
    }

    @GetMapping(path = "/users/{userId}/habits")
    public HttpEntity<Resources<HabitResource>> getHabits(
            @PathVariable("userId") final Long userId) {
        return new HttpEntity<>(service.getHabits(userId));
    }

    @GetMapping(path = "/users/{userId}/habits/{habitId}")
    public HttpEntity<HabitResource> getHabit(
            @PathVariable("userId") final Long userId,
            @PathVariable("habitId") final Long habitId) {
        return new HttpEntity<>(service.getHabit(userId, habitId));
    }

    @PatchMapping(path = "/users/{userId}/habits/{habitId}")
    public HttpEntity<HabitResource> patchHabit(@PathVariable("userId") final Long userId,
                                    @PathVariable("habitId") final Long habitId,
                                    @RequestBody final PatchHabitDto patch) {
        return new HttpEntity<>(service.patch(userId, habitId, patch));
    }
}
