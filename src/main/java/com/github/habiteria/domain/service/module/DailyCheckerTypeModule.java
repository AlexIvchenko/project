package com.github.habiteria.domain.service.module;

import com.github.habiteria.domain.model.CheckerType;
import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.model.Result;
import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.repository.ResultRepository;
import com.github.habiteria.domain.repository.UserRepository;
import com.github.habiteria.domain.service.HabitSnapshot;
import com.github.habiteria.utils.LocalDateRange;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Component
public class DailyCheckerTypeModule implements HabitCheckerTypeModule {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final ResultRepository resultRepository;

    public DailyCheckerTypeModule(UserRepository userRepository, HabitRepository habitRepository, ResultRepository resultRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public CheckerType supports() {
        return CheckerType.DAILY;
    }

    @Override
    public HabitSnapshot performHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Result result = Result.success(habit, date);
        resultRepository.save(result);
        return genSnapshot(habit, date, HabitSnapshot.Status.SUCCESS);
    }

    @Override
    public HabitSnapshot failHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Result result = Result.fail(habit, date);
        resultRepository.save(result);
        return genSnapshot(habit, date, HabitSnapshot.Status.FAIL);
    }

    // TODO decrease points
    @Override
    public HabitSnapshot undoHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Result result = resultRepository.findByHabitAndDate(habit, date);
        resultRepository.delete(result);
        return genSnapshot(habit, date, HabitSnapshot.Status.UNDEFINED);
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        User user = userRepository.findOne(userId.toString());
        Set<Habit> habits = habitRepository.findByOwner(user);
        Set<Result> fails = new HashSet<>();
        for (Habit habit : habits) {
            Set<Result> results = resultRepository.findByHabit(habit);
            LocalDate yesterday = LocalDate.now().minusDays(1);
            for (LocalDate date : new LocalDateRange(habit.getStart(), yesterday)) {
                boolean resultIsFound = false;
                for (Result result : results) {
                    if (result.getDate().equals(date)) {
                        resultIsFound = true;
                        break;
                    }
                }
                if (!resultIsFound) {
                    Result fail = Result.fail(habit, date);
                    fails.add(fail);
                }
            }
        }
        resultRepository.save(fails);
    }

    @Override
    public boolean thereAreUncheckedHabits(UUID userId) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return getHabits(userId, yesterday)
                .stream()
                .anyMatch(HabitSnapshot::isUnchecked);
    }

    @Override
    public HabitSnapshot createHabit(UUID userId, Habit habit) {
        User user = userRepository.findOne(userId.toString());
        habit.setOwner(user);
        habit = habitRepository.save(habit);
        return genSnapshot(habit, LocalDate.now(), HabitSnapshot.Status.UNDEFINED);
    }

    @Override
    public HabitSnapshot getHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        Result result = resultRepository.findByHabitAndDate(habit, date);
        if (result == null) {
            return genSnapshot(habit, date, HabitSnapshot.Status.UNDEFINED);
        } else {
            if (result.isSuccess()) {
                return genSnapshot(habit, date, HabitSnapshot.Status.SUCCESS);
            }
            else if (result.isFail()) {
                return genSnapshot(habit, date, HabitSnapshot.Status.FAIL);
            } else {
                return genSnapshot(habit, date, HabitSnapshot.Status.UNDEFINED);
            }
        }
    }

    @Override
    public Set<HabitSnapshot> getHabits(UUID userId, LocalDate date) {
        User owner = userRepository.findOne(userId.toString());
        Set<Result> results = resultRepository.findByDate(date);
        Set<Habit> habits = habitRepository.findByOwner(owner);
        Set<HabitSnapshot> snapshots = new HashSet<>();
        for (Habit habit : habits) {
            for (Result result : results) {
                if (result.getHabit().equals(habit)) {
                    if (result.isFail()) {
                        snapshots.add(genSnapshot(habit, date, HabitSnapshot.Status.FAIL));
                    }
                    if (result.isSuccess()) {
                        snapshots.add(genSnapshot(habit, date, HabitSnapshot.Status.SUCCESS));
                    }
                    break;
                }
            }
            HabitSnapshot snapshot = new HabitSnapshot(habit, date, true, true, HabitSnapshot.Status.UNDEFINED);
            snapshots.add(snapshot);
        }
        return snapshots;
    }

    private HabitSnapshot genSnapshot(Habit habit, LocalDate date, HabitSnapshot.Status status) {
        return new HabitSnapshot(habit, date, true, true, status);
    }
}
