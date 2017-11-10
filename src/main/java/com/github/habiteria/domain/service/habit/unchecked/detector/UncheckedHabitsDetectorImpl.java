package com.github.habiteria.domain.service.habit.unchecked.detector;

import com.github.habiteria.domain.service.habit.unchecked.module.UncheckedHabitsModules;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Primary
@Service
public class UncheckedHabitsDetectorImpl implements UncheckedHabitsDetector {
    private final UncheckedHabitsModules modules;

    public UncheckedHabitsDetectorImpl(UncheckedHabitsModules modules) {
        this.modules = modules;
    }

    @Override
    public boolean thereAreUncheckedHabits(UUID userId) {
        return modules.all().anyMatch(module -> module.thereAreUncheckedHabits(userId));
    }
}
