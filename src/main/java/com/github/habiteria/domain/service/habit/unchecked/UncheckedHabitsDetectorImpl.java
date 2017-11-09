package com.github.habiteria.domain.service.habit.unchecked;

import com.github.habiteria.domain.service.habit.unchecked.module.UncheckedHabitsDetectorModules;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Primary
@Service
public class UncheckedHabitsDetectorImpl implements UncheckedHabitsDetector {
    private final UncheckedHabitsDetectorModules modules;

    public UncheckedHabitsDetectorImpl(UncheckedHabitsDetectorModules modules) {
        this.modules = modules;
    }

    @Override
    public boolean thereAreUncheckedHabits(UUID userId) {
        return modules.all().anyMatch(module -> module.thereAreUncheckedHabits(userId));
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        modules.all().forEach(module -> module.failUncheckedHabits(userId));
    }
}
