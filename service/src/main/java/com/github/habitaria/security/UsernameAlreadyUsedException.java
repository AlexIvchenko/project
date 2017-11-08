package com.github.habitaria.security;

/**
 * @author Alex Ivchenko
 */
public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String message) {
        super(message);
    }
}
