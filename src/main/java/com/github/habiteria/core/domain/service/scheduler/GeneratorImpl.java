package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.exceptions.client.FutureScheduleRetrievingException;
import com.github.habiteria.exceptions.server.SequenceOfRepeatsBrokenException;
import com.github.habiteria.utils.LocalDateRange;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Service
public class GeneratorImpl implements Generator {
    private static final Predicate<LocalDate> DAILY_FILTER = date -> true;
    private static final Predicate<LocalDate> WEEKDAY_FILTER = date -> date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY;
    private static final Predicate<LocalDate> WEEKEND_FILTER = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;

    @Override
    public Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) {
        Set<CalendarRecord> generated = generate(habit);
        return generated.stream()
                .filter(record -> !record.getDate().isBefore(from) && !record.getDate().isAfter(to))
                .collect(Collectors.toSet());
    }

    @Override
    public CalendarRecord getOneRepeat(Habit habit, int repeat) {
        Set<CalendarRecord> all = getAllBetween(habit, habit.getSchedule().getStart().toLocalDate(), LocalDate.now());
        CalendarRecord last = null;
        CalendarRecord found = null;
        for (CalendarRecord record : all) {
            if (record.getRepeat() == repeat) {
                found = record;
            }
            if (last == null || record.getRepeat() > last.getRepeat()) {
                last = record;
            }
        }
        if (last == null || repeat > last.getRepeat()) {
            throw new FutureScheduleRetrievingException(habit, repeat);
        }
        if (found == null) {
            throw new SequenceOfRepeatsBrokenException(habit, repeat, last);
        }
        return found;
    }

    @Override
    public Set<CalendarRecord> getOnlyVerifiableIn(Set<Habit> habits, LocalDateTime time) {
        if (time.isAfter(LocalDateTime.now())) {
            throw new FutureScheduleRetrievingException(time);
        }
        Set<CalendarRecord> verifiable = new HashSet<>();
        for (Habit habit : habits) {
            generate(habit).stream()
                    .filter(rec -> rec.isVerifiableIn(time))
                    .forEach(verifiable::add);
        }
        return verifiable;
    }

    private Set<CalendarRecord> generate(Habit habit) {
        int repeat = 1;
        LocalDate from = habit.getSchedule().getStart().toLocalDate();
        LocalDate to = LocalDate.now();
        Predicate<LocalDate> filter = getFilterForType(habit.getSchedule().getType());
        Set<CalendarRecord> generated = new HashSet<>();
        for (LocalDate date : new LocalDateRange(from, to)) {
            if (filter.test(date)) {
                generated.add(build(habit, date, repeat++));
            }
        }
        return generated;
    }

    private Predicate<LocalDate> getFilterForType(Schedule.Type type) {
        switch (type) {
            case DAILY:
                return DAILY_FILTER;
            case WEEKDAY:
                return WEEKDAY_FILTER;
            case WEEKEND:
                return WEEKEND_FILTER;
            default:
                throw new IllegalArgumentException("filter for " + type.name() + " not found");
        }
    }

    private CalendarRecord build(Habit habit, LocalDate date, int repeat) {
        CalendarRecord record = new CalendarRecord();
        record.setHabit(habit);
        record.setRepeat(repeat);

        record.setStartDoing(date.atTime(LocalTime.MIN));
        record.setEndDoing(date.atTime(LocalTime.MAX));

        record.setStartVerifying(date.atTime(LocalTime.MIN));
        record.setEndVerifying(date.plusDays(1).atTime(LocalTime.MAX));

        if (record.getEndVerifying().isBefore(LocalDateTime.now())) {
            record.setStatus(Status.FAIL);
        } else {
            record.setStatus(Status.UNVERIFIED);
        }

        return record;
    }
}
