package com.github.habiteria.domain.service.habit.verifying.resolver;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UntrackedHabitsResolver {
    void failUncheckedHabits(UUID userId);
}
