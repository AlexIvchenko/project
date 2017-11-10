package com.github.habiteria.domain.service.habit;

import com.github.habiteria.domain.model.ScheduleType;

/**
 * @author Alex Ivchenko
 */
public interface Module {
    ScheduleType supports();
}
