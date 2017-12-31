package com.github.habiteria.exceptions.client.actions;

/**
 * @author Alex Ivchenko
 */
public class IllegalStateTransitionException extends IllegalActionException {
    public IllegalStateTransitionException(String message, ActionCode code, Object... args) {
        super(message, code, args);
    }
}
