package com.github.habiteria.integration.domain.resources;

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
    private final String name;
    private final String description;
    private final LocalDate startDate;
    private final int progress;

    public HabitResource(String name, String description, LocalDate startDate, int progress) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.progress = progress;
    }
}
