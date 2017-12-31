package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.service.calendar.CalendarService;
import com.github.habiteria.integration.domain.assemblers.CalendarResAsm;
import com.github.habiteria.integration.domain.resources.CalendarResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Service
public class CalendarResourceServiceImpl implements CalendarResourceService {
    private final CalendarService service;
    private final CalendarResAsm assembler;

    public CalendarResourceServiceImpl(CalendarService service, CalendarResAsm assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public CalendarResource getCalendar(Long habitId, LocalDate from, LocalDate to) {
        return assembler.toResource(service.getCalendar(habitId, from, to));
    }

    @Override
    public CalendarResource getCalendar(Long habitId) {
        return assembler.toResource(service.getCalendar(habitId));
    }
}
