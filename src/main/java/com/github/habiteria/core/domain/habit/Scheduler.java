package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.Habit;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
public interface Scheduler {
    void generate(Habit habit, LocalDateTime from, LocalDateTime to);
}
