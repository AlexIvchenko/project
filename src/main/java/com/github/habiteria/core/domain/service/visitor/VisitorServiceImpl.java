package com.github.habiteria.core.domain.service.visitor;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.Visitor;
import com.github.habiteria.core.repository.VisitorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public boolean visitedToday(User user) {
        boolean ret =  visitorRepository.existsByUserAndDate(user, LocalDate.now());
        log.info("" + ret);
        return ret;
    }

    @Override
    public void visit(User user) {
        System.out.println(visitorRepository.toString());
        if (!visitedToday(user)) {
            log.info("user {} haven't visited yet", user);
            visitorRepository.save(new Visitor(user));
        } else {
            log.info("user {} have already visited", user);
        }
    }
}
