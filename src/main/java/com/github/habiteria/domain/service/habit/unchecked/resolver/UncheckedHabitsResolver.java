package com.github.habiteria.domain.service.habit.unchecked.resolver;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UncheckedHabitsResolver {
    void failUncheckedHabits(UUID userId);
}
