package com.github.habiteria.domain.service.habit.unchecked;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UncheckedDetector {
    boolean thereAreUncheckedHabits(UUID userId);

    void failUncheckedHabits(UUID userId);
}
