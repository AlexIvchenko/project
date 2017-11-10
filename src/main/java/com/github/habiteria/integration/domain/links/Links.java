package com.github.habiteria.integration.domain.links;

import com.github.habiteria.integration.controller.AuthController;
import com.github.habiteria.integration.controller.HabitsController;
import com.github.habiteria.integration.controller.ResultsController;
import org.springframework.hateoas.Link;

import java.time.LocalDate;
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
        return linkTo(methodOn(HabitsController.class).createHabit(userId, null))
                .withRel("createHabit");
    }

    public static Link getHabits(UUID userId) {
        return linkTo(methodOn(HabitsController.class).getHabits(userId, LocalDate.now()))
                .withRel("getHabits");
    }

    public static Link getUncheckedHabits(UUID userId) {
        return linkTo(methodOn(HabitsController.class).getUncheckedHabits(userId))
                .withRel("getUnverifiedHabits");
    }

    public static Link perform(UUID userId, UUID habitId) {
        return linkTo(methodOn(HabitsController.class).perform(userId, habitId, LocalDate.now()))
                .withRel("performToday");
    }

    public static Link performYesterday(UUID userId, UUID habitId) {
        return linkTo(methodOn(HabitsController.class).perform(userId, habitId, LocalDate.now().minusDays(1))).
                withRel("performYesterday");
    }

    public static Link fail(UUID userId, UUID habitId) {
        return linkTo(methodOn(HabitsController.class).fail(userId, habitId, LocalDate.now()))
                .withRel("failToday");
    }

    public static Link failYesterday(UUID userId, UUID habitId) {
        return linkTo(methodOn(HabitsController.class).fail(userId, habitId, LocalDate.now().minusDays(1)))
                .withRel("failYesterday");
    }

    public static Link getResults(UUID userId, UUID habitId) {
        return linkTo(methodOn(ResultsController.class).getResults(userId, habitId))
                .withRel("getResults");
    }

    public static Link undo(UUID userId, UUID habitId) {
        return linkTo(methodOn(HabitsController.class).undoHabit(userId, habitId, LocalDate.now()))
                .withRel("undoToday");
    }
}
