package com.github.habiteria.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alex Ivchenko
 */
public class ApiErrorDto {
    private List<FieldErrorDto> fieldErrors = new ArrayList<>();
    private List<ResourceErrorDto> resourceErrors = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public void addFieldError(String field, String message) {
        fieldErrors.add(new FieldErrorDto(field, message));
    }

    public void addResourceError(String resource, String message) {
        resourceErrors.add(new ResourceErrorDto(resource, message));
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public List<FieldErrorDto> getFieldErrors() {
        return Collections.unmodifiableList(fieldErrors);
    }

    public List<ResourceErrorDto> getResourceErrors() {
        return Collections.unmodifiableList(resourceErrors);
    }
}
