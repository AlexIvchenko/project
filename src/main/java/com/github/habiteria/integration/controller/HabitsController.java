package com.github.habiteria.integration.controller;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.integration.domain.resources.HabitResource;
import com.github.habiteria.integration.domain.service.HabitResourceService;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(path = "/users/{userId}/habits/")
    public HttpEntity<Resources<HabitResource>> getHabits(
            @PathVariable("userId") final UUID userId,
            @RequestParam("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.getHabits(userId, date));
    }

    @GetMapping(path = "/users/{userId}/habits/unchecked")
    public HttpEntity<Resources<HabitResource>> getUncheckedHabits(
            @PathVariable("userId") final UUID userId) {
        return new HttpEntity<>(service.getUnverifiedHabits(userId));
    }

    @GetMapping(path = "/users/{userId}/habits/{habitId}")
    public HttpEntity<HabitResource> getHabit(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId,
            @RequestParam("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.getHabit(userId, habitId, date));
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/perform")
    public HttpEntity<HabitResource> perform(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId,
            @RequestParam("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.performHabit(userId, habitId, date));
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/fail")
    public HttpEntity<HabitResource> fail(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId,
            @RequestParam("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.failHabit(userId, habitId, date));
    }

    // TODO
    @PostMapping(path = "/users/{userId}/habits/check")
    public HttpEntity failUnverifiedHabits(
            @PathVariable("userId") final UUID userId) {

        return HttpEntity.EMPTY;
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/undo")
    public HttpEntity<HabitResource> undoHabit(@PathVariable("userId") final UUID userId,
                                               @PathVariable("habitId") final UUID habitId,
                                               @RequestParam("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.undoHabit(userId, habitId, date));
    }
}
