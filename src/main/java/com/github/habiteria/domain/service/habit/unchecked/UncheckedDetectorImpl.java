package com.github.habiteria.domain.service.habit.unchecked;

import com.github.habiteria.domain.service.habit.unchecked.module.UncheckedDetectorModules;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Primary
@Service
public class UncheckedDetectorImpl implements UncheckedDetector {
    private final UncheckedDetectorModules modules;

    public UncheckedDetectorImpl(UncheckedDetectorModules modules) {
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
