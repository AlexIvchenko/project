package com.github.habiteria.core.domain.visitor;

import com.github.habiteria.core.model.User;
import com.github.habiteria.core.model.Visitor;
import com.github.habiteria.core.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Service
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public boolean visitedToday(User user) {
        return visitorRepository.existsByUserAndDate(user, LocalDate.now());
    }

    @Override
    public void visit(User user) {
        if (!visitedToday(user)) {
            visitorRepository.save(new Visitor(user));
        }
    }
}
