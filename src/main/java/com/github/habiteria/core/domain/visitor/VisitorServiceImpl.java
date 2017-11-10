package com.github.habiteria.core.domain.visitor;

import com.github.habiteria.core.model.User;
import com.github.habiteria.core.model.Visitor;
import com.github.habiteria.core.repository.VisitorRepository;
import com.github.habiteria.core.domain.visitor.event.FirstVisitEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Service
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;
    private final ApplicationEventPublisher publisher;

    public VisitorServiceImpl(VisitorRepository visitorRepository, ApplicationEventPublisher publisher) {
        this.visitorRepository = visitorRepository;
        this.publisher = publisher;
    }

    @Override
    public boolean visitedToday(User user) {
        return visitorRepository.existsByUserAndDate(user, LocalDate.now());
    }

    @Override
    public Visitor visit(User user) {
        if (!visitedToday(user)) {
            Visitor visitor = visitorRepository.save(new Visitor(user));
            publisher.publishEvent(new FirstVisitEvent(visitor));
            return visitor;
        } else {
            return visitorRepository.getByUserAndDate(user, LocalDate.now());
        }
    }
}
