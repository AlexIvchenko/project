package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.habiteria.core.entities.Status;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class ScheduledHabitResource extends ResourceSupport {
    private final String name;
    private final String description;
    private final boolean verifiable;
    private final boolean required;
    private final Status status;
    private final int repeat;

    public ScheduledHabitResource(String name, String description, boolean verifiable, boolean required, Status status, int repeat) {
        this.name = name;
        this.description = description;
        this.verifiable = verifiable;
        this.required = required;
        this.status = status;
        this.repeat = repeat;
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
    public boolean isVerifiable() {
        return verifiable;
    }

    @JsonProperty
    public boolean isRequired() {
        return required;
    }

    @JsonProperty
    public Status getStatus() {
        return status;
    }

    @JsonProperty
    public int getRepeat() {
        return repeat;
    }
}
