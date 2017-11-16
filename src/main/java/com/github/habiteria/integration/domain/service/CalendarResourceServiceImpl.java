package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.service.calendar.CalendarService;
import com.github.habiteria.integration.domain.assembler.CalendarResourceAssembler;
import com.github.habiteria.integration.domain.resources.CalendarResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class CalendarResourceServiceImpl implements CalendarResourceService {
    private final CalendarService service;
    private final CalendarResourceAssembler assembler;

    public CalendarResourceServiceImpl(CalendarService service, CalendarResourceAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public CalendarResource getCalendar(UUID habitId, LocalDate from, LocalDate to) {
        return assembler.toResource(service.getCalendar(habitId, from, to));
    }
}
