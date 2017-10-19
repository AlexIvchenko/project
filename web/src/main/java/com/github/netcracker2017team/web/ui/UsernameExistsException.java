package com.github.netcracker2017team.web.ui;

/**
 * @author Alex Ivchenko
 */
public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(message);
    }
}
