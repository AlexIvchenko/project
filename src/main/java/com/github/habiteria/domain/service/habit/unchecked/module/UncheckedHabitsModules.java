package com.github.habiteria.domain.service.habit.unchecked.module;

import com.github.habiteria.domain.service.habit.Modules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Component
public class UncheckedHabitsModules extends Modules<UncheckedHabitsModule> {
    public UncheckedHabitsModules(List<UncheckedHabitsModule> modules) {
        super(modules);
    }
}
