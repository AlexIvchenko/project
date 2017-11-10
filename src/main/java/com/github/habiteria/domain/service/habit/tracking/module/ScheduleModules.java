package com.github.habiteria.domain.service.habit.tracking.module;

import com.github.habiteria.domain.service.habit.Modules;
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
