package com.github.habiteria.integration.domain.assemblers;

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
public class CalendarResAsm implements ResourceAssembler<Calendar, CalendarResource> {
    private final CalendarRecordResAsm assembler;

    public CalendarResAsm(CalendarRecordResAsm assembler) {
        this.assembler = assembler;
    }

    @Override
    public CalendarResource toResource(Calendar entity) {
        Long userId = entity.getHabit().getOwner().getId();
        Long habitId = entity.getHabit().getId();

        Set<CalendarRecordResource> records = ResourceUtils.toResourceSet(entity.getRecords(), assembler);
        Map<LocalDate, Set<CalendarRecordResource>> recordsMap = new HashMap<>();
        for (CalendarRecordResource record : records) {
            LocalDate date = record.getTime().toLocalDate();
            recordsMap.putIfAbsent(date, new HashSet<>());
            recordsMap.get(date).add(record);
        }
        CalendarResource resource = new CalendarResource(entity.getStart(), entity.getEnd(), recordsMap);
        resource.add(Links.getHabit(userId, habitId));
        return resource;
    }
}
