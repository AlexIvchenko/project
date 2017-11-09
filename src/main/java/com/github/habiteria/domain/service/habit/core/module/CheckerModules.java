package com.github.habiteria.domain.service.habit.core.module;

import com.github.habiteria.domain.service.habit.Modules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Component
public class CheckerModules extends Modules<HabitCheckerTypeModule> {
    public CheckerModules(List<HabitCheckerTypeModule> modules) {
        super(modules);
    }
}
