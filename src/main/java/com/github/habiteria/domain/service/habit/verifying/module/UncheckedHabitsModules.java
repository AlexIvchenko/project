package com.github.habiteria.domain.service.habit.verifying.module;

import com.github.habiteria.domain.service.habit.Modules;
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
