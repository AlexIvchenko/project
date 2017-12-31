package com.github.habiteria.dto;

import lombok.Getter;

/**
 * @author Alex Ivchenko
 */
@Getter
public class ResourceErrorDto {
    private final String resource;
    private final String message;

    public ResourceErrorDto(String resource, String message) {
        this.resource = resource;
        this.message = message;
    }
}
