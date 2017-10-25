package com.github.netcracker2017team.project.security;

/**
 * @author Alex Ivchenko
 */
public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String message) {
        super(message);
    }
}
