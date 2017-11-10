package com.github.habiteria.core.domain.visitor.event;

import com.github.habiteria.core.model.Visitor;

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
