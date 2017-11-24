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
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
@Primary
public class GeneratorImpl implements Generator {
    private final Generator daily;
    private final Generator weekend;
    private final HabitRepository habitRepository;

    public GeneratorImpl(@Qualifier("dailyGenerator") Generator daily,
                         @Qualifier("weekendGenerator") Generator weekend,
                         HabitRepository habitRepository) {
        this.daily = daily;
        this.weekend = weekend;
        this.habitRepository = habitRepository;
    }

    @Override
    public Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) {
        return findGeneratorFor(habit).getAllBetween(habit, from, to);
    }

    @Override
    public CalendarRecord getOneRepeat(Habit habit, int repeat) {
        return findGeneratorFor(habit).getOneRepeat(habit, repeat);
    }

    @Override
    public Set<CalendarRecord> getOnlyVerifiableIn(User user, LocalDateTime time) {
        for (Habit habit : habitRepository.findByOwner(user)) {
            if (habit.getSchedule().getType() != Schedule.Type.DAILY && habit.getSchedule().getType() != Schedule.Type.WEEKEND) {
                throw new IllegalArgumentException("cannot generate " + habit.getSchedule().getType() + " type");
            }
        }
        HashSet<CalendarRecord> result = new HashSet<>();
        result.addAll(daily.getOnlyVerifiableIn(user, time));
        result.addAll(weekend.getOnlyVerifiableIn(user, time));
        return result;
    }

    private Generator findGeneratorFor(Habit habit) {
        if (habit.getSchedule().getType() == Schedule.Type.DAILY) {
            return daily;
        } else if (habit.getSchedule().getType() == Schedule.Type.WEEKEND) {
            return weekend;
        } else {
            throw new IllegalArgumentException("cannot generate " + habit.getSchedule().getType() + " type");
        }
    }
}
