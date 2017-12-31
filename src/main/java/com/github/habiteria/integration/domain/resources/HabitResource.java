package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@ToString
public class HabitResource extends ResourceSupport {
    private final Long uid;
    private final String name;
    private final String description;
    private final LocalDate startDate;
    private final int progress;

    public HabitResource(Long uid, String name, String description, LocalDate startDate, int progress) {
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.progress = progress;
    }

    @JsonProperty("id")
    public Long getUid() {
        return uid;
    }
}
