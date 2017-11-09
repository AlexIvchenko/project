package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.model.Visitor;

/**
 * @author Alex Ivchenko
 */
public interface VisitorService {
    boolean visitedToday(User user);

    Visitor visit(User user);
}
