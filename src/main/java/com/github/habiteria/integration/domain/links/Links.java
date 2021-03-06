package com.github.habiteria.integration.domain.links;

import com.github.habiteria.integration.controller.*;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
public class Links {
    public static Link signUp() {
        return linkTo(methodOn(AuthController.class).create(null))
                .withRel("signUp");
    }

    public static Link createHabit(Long userId) {
        return linkTo(methodOn(HabitController.class).create(userId, null))
                .withRel("createHabit");
    }

    public static Link getHabitTracking(Long userId, Long habitId) {
        return linkTo(methodOn(TrackingController.class).getHabitTracking(userId, habitId))
                .withRel("getTracking");
    }

    public static Link getCurrentHabitList(Long userId) {
        return linkTo(methodOn(TrackingController.class).getCurrentHabitList(userId))
                .withRel("getCurrentHabitList");
    }

    public static Link perform(Long userId, Long habitId, int repeat) {
        return linkTo(methodOn(TrackingController.class).perform(userId, habitId, repeat))
                .withRel("perform");
    }


    public static Link fail(Long userId, Long habitId, int repeat) {
        return linkTo(methodOn(TrackingController.class).fail(userId, habitId, repeat))
                .withRel("fail");
    }

    public static Link undo(Long userId, Long habitId, int repeats) {
        return linkTo(methodOn(TrackingController.class).undo(userId, habitId, repeats))
                .withRel("undo");
    }

    public static Link getCalendar(Long userId, Long habitId) {
        return linkTo(methodOn(CalendarController.class).getCalendar(userId, habitId))
                .withRel("getCalendar");
    }

    public static Link getHabit(Long userId, Long habitId) {
        return linkTo(methodOn(HabitController.class).getHabit(userId, habitId))
                .withRel("getHabit");
    }

    public static Link getHabits(Long userId) {
        return linkTo(methodOn(HabitController.class).getHabits(userId))
                .withRel("getHabits");
    }

    public static Link updateKarma(Long userId) {
        return linkTo(methodOn(KarmaController.class).getKarma(userId))
                .withRel("update");
    }

    public static Link updateHabitDetails(Long userId, Long habitId) {
        return linkTo(methodOn(HabitController.class).patchHabit(userId, habitId, null))
                .withRel("update");
    }
}
