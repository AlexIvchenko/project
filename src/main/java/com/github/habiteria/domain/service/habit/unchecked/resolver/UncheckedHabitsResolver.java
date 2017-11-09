package com.github.habiteria.domain.service.habit.unchecked.resolver;

import com.github.habiteria.domain.service.visitor.event.FirstVisitEvent;
import org.springframework.context.event.EventListener;

/**
 * @author Alex Ivchenko
 */
public interface UncheckedHabitsResolver {
    @EventListener(FirstVisitEvent.class)
    void failUncheckedHabits(FirstVisitEvent event);
}
