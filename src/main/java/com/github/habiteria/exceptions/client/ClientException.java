package com.github.habiteria.exceptions.client;

/**
 * @author Alex Ivchenko
 */
public class ClientException extends RuntimeException {
    private final Code code;
    private final Object[] args;

    public ClientException(String message, Code code, Object... args) {
        super(message);
        this.code = code;
        this.args = args;
    }

    public Code getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }
}
