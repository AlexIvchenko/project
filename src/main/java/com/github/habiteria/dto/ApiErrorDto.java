package com.github.habiteria.dto;

import java.util.*;

/**
 * @author Alex Ivchenko
 */
public class ApiErrorDto {
    private Map<String, String> fieldErrors = new HashMap<>();
    private List<ResourceErrorDto> resourceErrors = new ArrayList<>();
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public void addFieldError(String field, String message) {
        fieldErrors.putIfAbsent(field, message);
    }

    public void addResourceError(String resource, String message) {
        resourceErrors.add(new ResourceErrorDto(resource, message));
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public Map<String, String> getFieldErrors() {
        return Collections.unmodifiableMap(fieldErrors);
    }

    public List<ResourceErrorDto> getResourceErrors() {
        return Collections.unmodifiableList(resourceErrors);
    }
}
