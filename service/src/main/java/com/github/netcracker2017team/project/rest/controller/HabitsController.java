package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.Habit;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.resources.HabitResource;
import com.github.netcracker2017team.project.rest.resources.HabitsResource;
import com.github.netcracker2017team.project.rest.service.HabitResourceService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Rest
public class HabitsController {
    private final HabitResourceService service;

    public HabitsController(HabitResourceService service) {
        this.service = service;
    }

    @PostMapping(path = "/users/{userId}/habits")
    public HttpEntity<HabitResource> createHabit(
            @PathVariable("userId") final UUID userId,
            @RequestBody final Habit habit) {
        return new HttpEntity<>(service.createHabit(userId, habit));
    }

    @GetMapping(path = "/users/{userId}/habits")
    public HttpEntity<HabitsResource> getHabits(
            @PathVariable("userId") final UUID userId) {
        return new HttpEntity<>(service.getDailyHabits(userId));
    }

    @GetMapping(path = "/users/{userId}/habits/{habitId}")
    public HttpEntity<HabitResource> getHabit(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId) {
        return new HttpEntity<>(service.getDailyHabit(userId, habitId));
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/perform")
    public HttpEntity<HabitResource> perform(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId) {
        return new HttpEntity<>(service.perform(userId, habitId));
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/fail")
    public HttpEntity<HabitResource> fail(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId) {
        return new HttpEntity<>(service.fail(userId, habitId));
    }
}
