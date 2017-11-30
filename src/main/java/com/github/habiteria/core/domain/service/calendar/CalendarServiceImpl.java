package com.github.habiteria.core.domain.service.calendar;

import com.github.habiteria.core.domain.model.Calendar;
import com.github.habiteria.core.domain.service.fetcher.StrictFetcher;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.exceptions.client.FutureScheduleRetrievingException;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    private final Scheduler scheduler;
    private final StrictFetcher fetcher;

    public CalendarServiceImpl(Scheduler scheduler, StrictFetcher fetcher) {
        this.scheduler = scheduler;
        this.fetcher = fetcher;
    }

    @Override
    public Calendar getCalendar(Long habitId, LocalDate from, LocalDate to) throws FutureScheduleRetrievingException {
        Habit habit = fetcher.fetchHabit(habitId);
        Set<CalendarRecord> records = scheduler.getRecords(habit, from, to);
        return new Calendar(habit, from, to, records);
    }

    @Override
    public Calendar getCalendar(Long habitId) {
        Habit habit = fetcher.fetchHabit(habitId);
        LocalDate from = habit.getSchedule().getStart().toLocalDate();
        LocalDate to = LocalDate.now();
        Set<CalendarRecord> records = scheduler.getRecords(habit, from, to);
        return new Calendar(habit, from, to, records);
    }
}
