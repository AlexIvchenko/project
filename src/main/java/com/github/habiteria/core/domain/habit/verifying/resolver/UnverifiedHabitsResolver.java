package com.github.habiteria.core.domain.habit.verifying.resolver;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UnverifiedHabitsResolver {
    void failUncheckedHabits(UUID userId);

    // TODO
//    void failNotVerifiableHabits(UUID uuid);

//    void failNotVerifiedVerifiableHabits(UUID uuid);
}
