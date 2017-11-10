package com.github.habiteria.core.domain.habit.verifying.module;

import com.github.habiteria.core.domain.habit.Modules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Component
public class UncheckedHabitsModules extends Modules<UnverifiedHabitsModule> {
    public UncheckedHabitsModules(List<UnverifiedHabitsModule> modules) {
        super(modules);
    }
}
