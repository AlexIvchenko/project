package com.github.habiteria.domain.service.habit.verifying.detector;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UnverifiedHabitsDetector {
    boolean thereAreUncheckedHabits(UUID userId);
}
