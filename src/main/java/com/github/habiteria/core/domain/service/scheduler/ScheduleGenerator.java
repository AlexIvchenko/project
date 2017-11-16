package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;

/**
 * @author Alex Ivchenko
 */
public interface ScheduleGenerator {
    void generateAll(User user);

    void generate(Habit habit);
}
