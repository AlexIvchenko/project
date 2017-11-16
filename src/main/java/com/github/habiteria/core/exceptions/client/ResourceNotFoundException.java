package com.github.habiteria.core.exceptions.client;

/**
 * @author Alex Ivchenko
 */
public class ResourceNotFoundException extends ClientException {
    public ResourceNotFoundException(String type, Long id) {
        super(type + " with id: " + id + " not found");
    }
}
