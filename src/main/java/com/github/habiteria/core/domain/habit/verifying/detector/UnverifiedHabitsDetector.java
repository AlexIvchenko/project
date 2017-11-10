package com.github.habiteria.core.domain.habit.verifying.detector;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UnverifiedHabitsDetector {
    boolean thereAreUncheckedHabits(UUID userId);

    // TODO
//    boolean thereAreNotVerifiableHabits(UUID userId);
//
//    boolean thereAreVerifiableHabits(UUID userId);
//
//    Set<HabitSnapshot> getVerifiableHabits(UUID userId);
}
