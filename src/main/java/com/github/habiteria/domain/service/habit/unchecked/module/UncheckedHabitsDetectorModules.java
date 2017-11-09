package com.github.habiteria.domain.service.habit.unchecked.module;

import com.github.habiteria.domain.service.habit.Modules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Component
public class UncheckedHabitsDetectorModules extends Modules<UncheckedHabitsDetectorModule> {
    public UncheckedHabitsDetectorModules(List<UncheckedHabitsDetectorModule> modules) {
        super(modules);
    }
}
