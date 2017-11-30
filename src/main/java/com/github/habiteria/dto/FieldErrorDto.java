package com.github.habiteria.dto;

import lombok.Getter;

/**
 * @author Alex Ivchenko
 */
@Getter
public class FieldErrorDto {
    private String field;
    private String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
