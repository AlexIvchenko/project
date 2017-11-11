package com.github.habiteria.integration.domain.links;

import com.github.habiteria.integration.controller.AuthController;
import com.github.habiteria.integration.controller.HabitController;
import com.github.habiteria.integration.controller.TrackingController;
import org.springframework.hateoas.Link;

import java.util.UUID;

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

    public static Link createHabit(UUID userId) {
        return linkTo(methodOn(HabitController.class).create(userId, null))
                .withRel("create");
    }

    public static Link getCurrentHabitList(UUID userId) {
        return linkTo(methodOn(TrackingController.class).getCurrentHabitList(userId))
                .withRel("getCurrentHabitList");
    }

    public static Link perform(UUID userId, UUID habitId, int repeat) {
        return linkTo(methodOn(TrackingController.class).perform(userId, habitId, repeat))
                .withRel("perform");
    }


    public static Link fail(UUID userId, UUID habitId, int repeat) {
        return linkTo(methodOn(TrackingController.class).fail(userId, habitId, repeat))
                .withRel("fail");
    }

    public static Link undo(UUID userId, UUID habitId, int repeats) {
        return linkTo(methodOn(TrackingController.class).undo(userId, habitId, repeats))
                .withRel("undo");
    }
}
