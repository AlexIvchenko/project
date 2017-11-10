package com.github.habiteria.domain.service.habit.unchecked.resolver;

import com.github.habiteria.domain.service.habit.unchecked.module.UncheckedHabitsModules;
import com.github.habiteria.domain.service.visitor.event.FirstVisitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Component
public class UncheckedHabitsResolverImpl implements UncheckedHabitsResolver {
    private final UncheckedHabitsModules modules;

    public UncheckedHabitsResolverImpl(UncheckedHabitsModules modules) {
        this.modules = modules;
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        modules.all().forEach(module -> module.failUncheckedHabits(userId));
    }

    @EventListener(FirstVisitEvent.class)
    public void failUncheckedHabits(FirstVisitEvent event) {
        log.info("first visiting from user {}", event.getVisitor().getUser());
        UUID userId = UUID.fromString(event.getVisitor().getUser().getId());
        failUncheckedHabits(userId);
    }
}
