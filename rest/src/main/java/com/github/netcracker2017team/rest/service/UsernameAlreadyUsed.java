package com.github.netcracker2017team.rest.service;

/**
 * @author Alex Ivchenko
 */
public class UsernameAlreadyUsed extends RuntimeException {
    public UsernameAlreadyUsed(String message) {
        super(message);
    }
}
