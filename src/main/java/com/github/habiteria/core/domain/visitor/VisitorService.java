package com.github.habiteria.core.domain.visitor;

import com.github.habiteria.core.model.User;

/**
 * @author Alex Ivchenko
 */
public interface VisitorService {
    boolean visitedToday(User user);

    void visit(User user);
}
