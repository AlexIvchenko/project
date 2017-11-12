package com.github.habiteria.integration.domain.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class CalendarResource extends ResourceSupport {
    private final LocalDate start;
    private final LocalDate end;
    private final Map<LocalDate, Set<CalendarRecordResource>> records;

    public CalendarResource(LocalDate start, LocalDate end, Map<LocalDate, Set<CalendarRecordResource>> records) {
        this.start = start;
        this.end = end;
        this.records = records;
    }

    @JsonProperty
    public LocalDate getStart() {
        return start;
    }

    @JsonProperty
    public LocalDate getEnd() {
        return end;
    }

    @JsonProperty
    public Map<LocalDate, Set<CalendarRecordResource>> getRecords() {
        return Collections.unmodifiableMap(records);
    }
}
