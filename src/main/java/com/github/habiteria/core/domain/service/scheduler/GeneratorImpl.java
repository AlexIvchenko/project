package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
@Primary
public class GeneratorImpl implements Generator {
    private final Generator daily;
    private final HabitRepository habitRepository;

    public GeneratorImpl(@Qualifier("dailyGenerator") Generator daily, HabitRepository habitRepository) {
        this.daily = daily;
        this.habitRepository = habitRepository;
    }

    @Override
    public Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) {
        if (habit.getSchedule().getType() == Schedule.Type.DAILY) {
            return daily.getAllBetween(habit, from, to);
        } else {
            throw new IllegalArgumentException("cannot generate " + habit.getSchedule().getType() + " type");
        }
    }

    @Override
    public CalendarRecord getOneRepeat(Habit habit, int repeat) {
        if (habit.getSchedule().getType() == Schedule.Type.DAILY) {
            return daily.getOneRepeat(habit, repeat);
        } else {
            throw new IllegalArgumentException("cannot generate " + habit.getSchedule().getType() + " type");
        }
    }

    @Override
    public Set<CalendarRecord> getOnlyVerifiableIn(User user, LocalDateTime time) {
        for (Habit habit : habitRepository.findByOwner(user)) {
            if (habit.getSchedule().getType() != Schedule.Type.DAILY) {
                throw new IllegalArgumentException("cannot generate " + habit.getSchedule().getType() + " type");
            }
        }
        return daily.getOnlyVerifiableIn(user, time);
    }
}
