package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.ScheduleType;

/**
 * @author Alex Ivchenko
 */
public interface Module {
    ScheduleType supports();
}
