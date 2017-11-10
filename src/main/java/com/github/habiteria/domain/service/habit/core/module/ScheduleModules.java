package com.github.habiteria.domain.service.habit.core.module;

import com.github.habiteria.domain.service.habit.Modules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Component
public class ScheduleModules extends Modules<ScheduleModule> {
    public ScheduleModules(List<ScheduleModule> modules) {
        super(modules);
    }
}
