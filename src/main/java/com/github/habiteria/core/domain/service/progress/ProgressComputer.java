package com.github.habiteria.core.domain.service.progress;

import com.github.habiteria.core.entities.Habit;

/**
 * @author Alex Ivchenko
 */
public interface ProgressComputer {
    int compute(Habit habit);
}
