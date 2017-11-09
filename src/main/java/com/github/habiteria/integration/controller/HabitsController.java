package com.github.habiteria.integration.controller;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.integration.resources.HabitResource;
import com.github.habiteria.integration.resources.HabitsResource;
import com.github.habiteria.integration.service.HabitResourceService;
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
    public HttpEntity<HabitsResource> getHabits(
            @PathVariable("userId") final UUID userId,
            @RequestParam("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.getHabits(userId, date));
    }

    @GetMapping(path = "/users/{userId}/habits/unchecked")
    public HttpEntity<HabitsResource> getUncheckedHabits(
            @PathVariable("userId") final UUID userId) {
        return new HttpEntity<>(service.getUncheckedHabits(userId));
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

    @PostMapping(path = "/users/{userId}/habits/check")
    public HttpEntity failUncheckedHabits(
            @PathVariable("userId") final UUID userId) {
        service.failUncheckedHabits(userId);
        return HttpEntity.EMPTY;
    }

    @PostMapping(path = "/users/{userId}/habits/{habitId}/undo")
    public HttpEntity<HabitResource> undoHabit(@PathVariable("userId") final UUID userId,
                                               @PathVariable("habitId") final UUID habitId,
                                               @PathVariable("date") @Date LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return new HttpEntity<>(service.undoHabit(userId, habitId, date));
    }
}
