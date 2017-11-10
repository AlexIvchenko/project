package com.github.habiteria.core.domain.habit.tracking.module;

import com.github.habiteria.core.domain.habit.Modules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Component
public class ScheduleModules extends Modules<TrackingModule> {
    public ScheduleModules(List<TrackingModule> modules) {
        super(modules);
    }
}
