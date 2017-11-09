package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.model.Visitor;
import com.github.habiteria.domain.repository.VisitorRepository;
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
    public Visitor visit(User user) {
        if (!visitedToday(user)) {
            Visitor visitor = new Visitor(user);
            return visitorRepository.save(visitor);
        } else {
            return visitorRepository.getByUserAndDate(user, LocalDate.now());
        }
    }
}
