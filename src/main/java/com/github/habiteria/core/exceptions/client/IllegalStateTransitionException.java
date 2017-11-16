package com.github.habiteria.core.exceptions.client;

/**
 * @author Alex Ivchenko
 */
public class IllegalStateTransitionException extends ClientException {
    public IllegalStateTransitionException() {
        super();
    }

    public IllegalStateTransitionException(String message) {
        super(message);
    }

    public IllegalStateTransitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalStateTransitionException(Throwable cause) {
        super(cause);
    }

    protected IllegalStateTransitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
