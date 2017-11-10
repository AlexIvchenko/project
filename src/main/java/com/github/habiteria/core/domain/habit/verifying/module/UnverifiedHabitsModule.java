package com.github.habiteria.core.domain.habit.verifying.module;

import com.github.habiteria.core.domain.habit.Module;
import com.github.habiteria.core.domain.habit.verifying.detector.UnverifiedHabitsDetector;
import com.github.habiteria.core.domain.habit.verifying.resolver.UnverifiedHabitsResolver;

/**
 * @author Alex Ivchenko
 */
public interface UnverifiedHabitsModule extends Module,
        UnverifiedHabitsDetector,
        UnverifiedHabitsResolver {

}
