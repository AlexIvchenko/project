package com.github.habiteria.security;

/**
 * @author Alex Ivchenko
 */
public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String message) {
        super(message);
    }
}
