package com.github.habiteria.domain.service.habit.verifying.detector;

import com.github.habiteria.domain.service.habit.verifying.module.UncheckedHabitsModules;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Primary
@Service
public class UnverifiedHabitsDetectorImpl implements UnverifiedHabitsDetector {
    private final UncheckedHabitsModules modules;

    public UnverifiedHabitsDetectorImpl(UncheckedHabitsModules modules) {
        this.modules = modules;
    }

    @Override
    public boolean thereAreUncheckedHabits(UUID userId) {
        return modules.all().anyMatch(module -> module.thereAreUncheckedHabits(userId));
    }
}
