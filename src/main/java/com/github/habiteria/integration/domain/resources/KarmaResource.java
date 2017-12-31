package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
public class KarmaResource extends ResourceSupport {
    private final int value;
    private final LocalDateTime actualTime;

    public KarmaResource(int value, LocalDateTime actualTime) {
        this.value = value;
        this.actualTime = actualTime;
    }

    @JsonProperty
    public int getValue() {
        return value;
    }

    @JsonProperty
    public LocalDateTime getActualTime() {
        return actualTime;
    }
}
