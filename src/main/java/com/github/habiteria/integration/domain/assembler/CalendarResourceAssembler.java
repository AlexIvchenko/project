package com.github.habiteria.integration.domain.assembler;

import com.github.habiteria.core.domain.model.Calendar;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import com.github.habiteria.integration.domain.resources.CalendarResource;
import com.github.habiteria.integration.domain.utils.ResourceUtils;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Alex Ivchenko
 */
@Component
public class CalendarResourceAssembler implements ResourceAssembler<Calendar, CalendarResource> {
    private final CalendarRecordResourceAssembler assembler;

    public CalendarResourceAssembler(CalendarRecordResourceAssembler assembler) {
        this.assembler = assembler;
    }

    @Override
    public CalendarResource toResource(Calendar entity) {
        UUID userId = UUID.fromString(entity.getHabit().getOwner().getId());
        UUID habitId = UUID.fromString(entity.getHabit().getId());

        Set<CalendarRecordResource> records = ResourceUtils.toResourceSet(entity.getRecords(), assembler);
        Map<LocalDate, Set<CalendarRecordResource>> recordsMap = new HashMap<>();
        for (CalendarRecordResource record : records) {
            LocalDate date = record.getTime().toLocalDate();
            recordsMap.putIfAbsent(date, new HashSet<>());
            recordsMap.get(date).add(record);
        }
        CalendarResource resource = new CalendarResource(entity.getStart(), entity.getEnd(), recordsMap);
        resource.add(Links.getHabitCard(userId, habitId));
        return resource;
    }
}
