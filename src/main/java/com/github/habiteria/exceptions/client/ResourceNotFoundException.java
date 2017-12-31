package com.github.habiteria.exceptions.client;

/**
 * @author Alex Ivchenko
 */
// TODO make IllegalActionException
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String type, Long id) {
        super(type + " with id: " + id + " not found");
    }
}
