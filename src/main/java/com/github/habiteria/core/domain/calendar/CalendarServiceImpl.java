package com.github.habiteria.core.domain.calendar;

import com.github.habiteria.core.domain.habit.Scheduler;
import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

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
    public Calendar getCalendar(UUID habitId, LocalDate from, LocalDate to) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Set<CalendarRecord> records = scheduler.getRecords(habit, from, to);
        return new Calendar(habit, from, to, records);
    }
}
