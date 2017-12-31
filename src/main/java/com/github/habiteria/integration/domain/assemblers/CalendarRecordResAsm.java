package com.github.habiteria.integration.domain.assemblers;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.integration.controller.CalendarRecordController;
import com.github.habiteria.integration.controller.HabitController;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class CalendarRecordResAsm implements ResourceAssembler<CalendarRecord, CalendarRecordResource> {
    @Override
    public CalendarRecordResource toResource(CalendarRecord entity) {
        CalendarRecordResource resource = new CalendarRecordResource(entity.getStartDoing(), entity.getRepeat(), entity.getStatus());

        Long userId = entity.getHabit().getOwner().getId();
        Long habitId = entity.getHabit().getId();
        int repeat = entity.getRepeat();

        resource.add(linkTo(methodOn(CalendarRecordController.class).getRecord(userId, habitId, repeat))
                .withSelfRel());

        resource.add(linkTo(methodOn(HabitController.class).getHabit(userId, habitId))
                .withRel("getHabit"));

        if (entity.isVerifiableIn(LocalDateTime.now())) {
            if (entity.isUnverified()) {
                resource.add(Links.perform(userId, habitId, entity.getRepeat()));
                resource.add(Links.fail(userId, habitId, entity.getRepeat()));
            } else {
                resource.add(Links.undo(userId, habitId, entity.getRepeat()));
            }
        }
        return resource;
    }
}
