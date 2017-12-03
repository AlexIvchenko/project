package com.github.habiteria.integration.domain.resources;

import com.github.habiteria.core.entities.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public class ScheduledHabitResource extends ResourceSupport {
    private final String name;
    private final String description;
    private final boolean verifiable;
    private final boolean required;
    private final Status status;
    private final int repeat;
    private final LocalDate date;

    public ScheduledHabitResource(String name, String description, boolean verifiable, boolean required, Status status, int repeat, LocalDate date) {
        this.name = name;
        this.description = description;
        this.verifiable = verifiable;
        this.required = required;
        this.status = status;
        this.repeat = repeat;
        this.date = date;
    }
}
