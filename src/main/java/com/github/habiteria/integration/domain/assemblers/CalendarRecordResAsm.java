package com.github.habiteria.integration.domain.assemblers;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class CalendarRecordResAsm implements ResourceAssembler<CalendarRecord, CalendarRecordResource> {
    @Override
    public CalendarRecordResource toResource(CalendarRecord entity) {
        return new CalendarRecordResource(entity.getStartDoing(), entity.getRepeat(), entity.getStatus());
    }
}
