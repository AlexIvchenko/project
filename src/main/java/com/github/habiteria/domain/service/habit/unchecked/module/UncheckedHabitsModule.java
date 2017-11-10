package com.github.habiteria.domain.service.habit.unchecked.module;

import com.github.habiteria.domain.service.habit.Module;
import com.github.habiteria.domain.service.habit.unchecked.detector.UncheckedHabitsDetector;
import com.github.habiteria.domain.service.habit.unchecked.resolver.UncheckedHabitsResolver;

/**
 * @author Alex Ivchenko
 */
public interface UncheckedHabitsModule extends Module,
        UncheckedHabitsDetector,
        UncheckedHabitsResolver {

}
