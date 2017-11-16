package com.github.habiteria.core.domain.service.visitor;

import com.github.habiteria.core.entities.User;

/**
 * @author Alex Ivchenko
 */
public interface VisitorService {
    boolean visitedToday(User user);

    void visit(User user);
}
