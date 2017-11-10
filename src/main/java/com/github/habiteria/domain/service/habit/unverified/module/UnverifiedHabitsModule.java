package com.github.habiteria.domain.service.habit.unverified.module;

import com.github.habiteria.domain.service.habit.Module;
import com.github.habiteria.domain.service.habit.unverified.detector.UnverifiedHabitsDetector;
import com.github.habiteria.domain.service.habit.unverified.resolver.UntrackedHabitsResolver;

/**
 * @author Alex Ivchenko
 */
public interface UnverifiedHabitsModule extends Module,
        UnverifiedHabitsDetector,
        UntrackedHabitsResolver {

}
