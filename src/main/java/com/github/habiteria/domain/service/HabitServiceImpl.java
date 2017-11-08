package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.*;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.repository.ResultRepository;
import com.github.habiteria.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitServiceImpl implements HabitService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final ResultRepository resultRepository;

    public HabitServiceImpl(UserRepository userRepository, HabitRepository habitRepository, ResultRepository resultRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public Set<Habit> getHabits(UUID userId) {
        User owner = userRepository.findOne(userId.toString());
        return habitRepository.findByOwner(owner);
    }

    @Override
    public Habit getHabit(UUID userId, UUID habitId) {
        return habitRepository.findOne(habitId.toString());
    }

    @Override
    public Habit perform(UUID userId, UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Result result = new Result(habit, Result.Status.SUCCESS);
        resultRepository.save(result);
        return habit;
    }

    @Override
    public Habit fail(UUID userId, UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Result result = new Result(habit, Result.Status.FAIL);
        resultRepository.save(result);
        return habit;
    }

    @Override
    public Habit createHabit(UUID userId, Habit habit) {
        User owner = userRepository.findOne(userId.toString());
        habit.setOwner(owner);
        return habitRepository.save(habit);
    }

    @Override
    public boolean isAvailableToDoToday(Habit habit) {
        if (habit.getChecker().getType() == CheckerType.DAILY) {
            return resultRepository.countByHabitAndDate(habit, LocalDate.now()) == 0;
        } else if (habit.getChecker().getType() == CheckerType.WEEKLY) {
            WeeklyChecker checker = (WeeklyChecker) habit.getChecker();
            Set<Result> results = resultRepository.findByHabit(habit);
            LocalDate now = LocalDate.now();
            LocalDate lastWeekStarted = now.minusDays(now.getDayOfWeek().getValue());
            long resultsPerLastWeek = results.stream().filter(result -> !result.getDate().isBefore(lastWeekStarted))
                    .count();
            long resultsToday = results.stream().filter(result -> result.getDate().isEqual(now)).count();
            return resultsPerLastWeek < checker.getRepeats() && resultsToday == 0;
        } else {
            throw new IllegalArgumentException("unsupported " + CheckerType.class.getName() + ": " + habit.getChecker().getType());
        }
    }
}
