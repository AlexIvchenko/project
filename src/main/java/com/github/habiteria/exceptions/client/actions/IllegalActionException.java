package com.github.habiteria.exceptions.client.actions;

import com.github.habiteria.exceptions.client.ClientException;

/**
 * @author Alex Ivchenko
 */
public class IllegalActionException extends ClientException {
    private final ActionCode code;
    public IllegalActionException(String message, ActionCode code, Object... args) {
        super(message, code, args);
        this.code = code;
    }

    public String getResource() {
        return code.getType();
    }

    @Override
    public ActionCode getCode() {
        return code;
    }
}
