package com.github.habiteria.vaadin.ui;

/**
 * @author Alex Ivchenko
 */
public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(message);
    }
}
