package com.github.habiteria.integration.domain.assemblers;

import com.github.habiteria.core.domain.service.progress.ProgressComputer;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Component
public class HabitResAsm implements ResourceAssembler<Habit, HabitResource> {
    private final ProgressComputer progressComputer;

    public HabitResAsm(ProgressComputer progressComputer) {
        this.progressComputer = progressComputer;
    }

    @Override
    public HabitResource toResource(Habit entity) {
        LocalDate startDate = entity.getSchedule().getStart().toLocalDate();
        int progress = progressComputer.compute(entity);
        HabitResource resource = new HabitResource(entity.getName(), entity.getDescription(), startDate, progress);
        Long userId = entity.getOwner().getId();
        Long habitId = entity.getId();
        resource.add(Links.getCalendar(userId, habitId));
        return resource;
    }
}
