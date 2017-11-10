package com.github.habiteria.core.domain.habit.verifying.resolver;

import com.github.habiteria.core.domain.habit.verifying.module.UncheckedHabitsModules;
import com.github.habiteria.core.domain.visitor.event.FirstVisitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Component
public class UnverifiedHabitsResolverImpl implements UnverifiedHabitsResolver {
    private final UncheckedHabitsModules modules;

    public UnverifiedHabitsResolverImpl(UncheckedHabitsModules modules) {
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
