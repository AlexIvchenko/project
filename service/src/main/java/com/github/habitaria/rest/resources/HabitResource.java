package com.github.habitaria.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class HabitResource extends ResourceSupport {
    private final String name;
    private final String description;

    public HabitResource(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }
}
