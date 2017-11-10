package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.habiteria.core.model.Result;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class ResultResource extends ResourceSupport {
    private final LocalDate date;
    private final Result.Status status;

    public ResultResource(LocalDate date, Result.Status status) {
        this.date = date;
        this.status = status;
    }

    @JsonProperty("date")
    public LocalDate getDate() {
        return date;
    }

    @JsonProperty("status")
    public Result.Status getStatus() {
        return status;
    }
}
