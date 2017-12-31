package com.github.habiteria.exceptions.client.fields;

import com.github.habiteria.exceptions.client.ClientException;

/**
 * @author Alex Ivchenko
 */
public class IllegalFieldValueException extends ClientException {
    private final FieldCode code;

    public IllegalFieldValueException(String message, FieldCode code, Object... args) {
        super(message, code, args);
        this.code = code;
    }

    public String getField() {
        return code.getField();
    }

    @Override
    public FieldCode getCode() {
        return code;
    }
}
