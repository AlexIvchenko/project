package com.github.habiteria.core.domain.service.calendar;

import com.github.habiteria.core.domain.model.Calendar;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    private final Scheduler scheduler;
    private final HabitRepository habitRepository;

    public CalendarServiceImpl(Scheduler scheduler, HabitRepository habitRepository) {
        this.scheduler = scheduler;
        this.habitRepository = habitRepository;
    }

    @Override
    public Calendar getCalendar(Long habitId, LocalDate from, LocalDate to) {
        Habit habit = habitRepository.findOne(habitId);
        Set<CalendarRecord> records = scheduler.getRecords(habit, from, to);
        return new Calendar(habit, from, to, records);
    }
}
