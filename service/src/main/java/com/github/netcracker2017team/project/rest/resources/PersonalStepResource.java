package com.github.netcracker2017team.project.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class PersonalStepResource extends ResourceSupport {
    private final String name;
    private final String description;

    @JsonCreator
    public PersonalStepResource(String name, String description) {
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
