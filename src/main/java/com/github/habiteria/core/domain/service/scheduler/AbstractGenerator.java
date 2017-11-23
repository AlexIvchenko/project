package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.exceptions.client.FutureScheduleRetrievingException;
import com.github.habiteria.core.exceptions.server.SequenceOfRepeatsBrokenException;
import com.github.habiteria.core.repository.HabitRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public abstract class AbstractGenerator implements Generator {
    private HabitRepository habitRepository;

    public AbstractGenerator(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
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
    public Set<CalendarRecord> getOnlyVerifiableIn(User user, LocalDateTime time) {
        Set<Habit> habits = habitRepository.findByOwner(user);
        Set<CalendarRecord> verifiable = new HashSet<>();
        for (Habit habit : habits) {
            getAllBetween(habit, habit.getSchedule().getStart().toLocalDate(), time.plusDays(1).toLocalDate())
                    .stream()
                    .filter(record -> !record.getStartVerifying().isAfter(time) && !record.getEndVerifying().isBefore(time))
                    .forEach(verifiable::add);

        }
        return verifiable;
    }
}
