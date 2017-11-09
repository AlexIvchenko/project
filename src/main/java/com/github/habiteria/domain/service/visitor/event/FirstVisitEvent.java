package com.github.habiteria.domain.service.visitor.event;

import com.github.habiteria.domain.model.Visitor;

/**
 * @author Alex Ivchenko
 */
public class FirstVisitEvent {
    private final Visitor visitor;

    public FirstVisitEvent(Visitor visitor) {
        this.visitor = visitor;
    }

    public Visitor getVisitor() {
        return visitor;
    }
}
