package com.github.habitaria.vaadin.ui;

/**
 * @author Alex Ivchenko
 */
public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(message);
    }
}
