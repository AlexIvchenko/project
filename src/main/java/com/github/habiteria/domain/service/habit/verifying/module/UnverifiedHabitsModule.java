package com.github.habiteria.domain.service.habit.verifying.module;

import com.github.habiteria.domain.service.habit.Module;
import com.github.habiteria.domain.service.habit.verifying.detector.UnverifiedHabitsDetector;
import com.github.habiteria.domain.service.habit.verifying.resolver.UntrackedHabitsResolver;

/**
 * @author Alex Ivchenko
 */
public interface UnverifiedHabitsModule extends Module,
        UnverifiedHabitsDetector,
        UntrackedHabitsResolver {

}
