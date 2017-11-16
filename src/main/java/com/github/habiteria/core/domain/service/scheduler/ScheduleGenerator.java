package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
public interface ScheduleGenerator {
    void generateAll(User user);

    void generate(Habit habit);

    void generate(Habit habit, LocalDateTime to);

    void doGenerate(Habit habit, int nextRepeat, LocalDateTime to);
}
