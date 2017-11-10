package com.github.habiteria.core.domain.visitor;

import com.github.habiteria.core.model.User;
import com.github.habiteria.core.model.Visitor;

/**
 * @author Alex Ivchenko
 */
public interface VisitorService {
    boolean visitedToday(User user);

    Visitor visit(User user);
}
