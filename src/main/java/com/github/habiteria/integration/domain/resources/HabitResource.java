package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.habiteria.core.domain.habit.tracking.HabitSnapshot;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class HabitResource extends ResourceSupport {
    private final String name;
    private final String description;
    private final boolean available;
    private final boolean required;
    private final HabitSnapshot.Status status;

    public HabitResource(String name, String description, boolean available, boolean required, HabitSnapshot.Status status) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.required = required;
        this.status = status;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public boolean isAvailable() {
        return available;
    }

    @JsonProperty
    public boolean isRequired() {
        return required;
    }

    @JsonProperty
    public HabitSnapshot.Status getStatus() {
        return status;
    }
}
