package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.habiteria.core.model.Status;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class CalendarRecordResource extends ResourceSupport {
    private final LocalDateTime time;
    private final int repeat;
    private final Status status;

    public CalendarRecordResource(LocalDateTime time, int repeat, Status status) {
        this.time = time;
        this.repeat = repeat;
        this.status = status;
    }

    @JsonProperty
    public LocalDateTime getTime() {
        return time;
    }

    @JsonProperty
    public int getRepeat() {
        return repeat;
    }

    @JsonProperty
    public Status getStatus() {
        return status;
    }
}
