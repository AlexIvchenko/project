package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.CheckerType;
import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.service.module.HabitCheckerTypeModule;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alex Ivchenko
 */
@Primary
@Service("habitSnapshotService")
public class HabitSnapshotServiceImpl implements HabitSnapshotService {
    private final HabitRepository habitRepository;
    private final Map<CheckerType, HabitCheckerTypeModule> modules;

    public HabitSnapshotServiceImpl(HabitRepository habitRepository,
                            List<HabitCheckerTypeModule> modules) {
        this.habitRepository = habitRepository;
        this.modules = modules.stream()
                .collect(Collectors.toMap(HabitCheckerTypeModule::supports, module -> module));
    }

    @Override
    public HabitSnapshot createHabit(UUID userId, Habit habit) {
        return module(habit.getChecker().getType()).createHabit(userId, habit);
    }

    @Override
    public HabitSnapshot getHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        return module(habit.getChecker().getType()).getHabit(userId, habitId, date);
    }

    @Override
    public Set<HabitSnapshot> getHabits(UUID userId, LocalDate date) {
        return modules()
                .map(module -> module.getHabits(userId, date))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private HabitCheckerTypeModule module(CheckerType type) {
        return modules.get(type);
    }

    @Override
    public HabitSnapshot performHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        HabitCheckerTypeModule module = module(habit.getChecker().getType());
        return module.performHabit(userId, habitId, date);
    }

    @Override
    public HabitSnapshot failHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        HabitCheckerTypeModule module = module(habit.getChecker().getType());
        return module.failHabit(userId, habitId, date);
    }

    @Override
    public HabitSnapshot undoHabit(UUID userId, UUID habitId, LocalDate date) {
        Habit habit = habitRepository.findOne(habitId.toString());
        HabitCheckerTypeModule module = module(habit.getChecker().getType());
        return module.undoHabit(userId, habitId, date);
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        modules().forEach(module -> failUncheckedHabits(userId));
    }

    @Override
    public boolean thereAreUncheckedHabits(UUID userId) {
        return modules().anyMatch(module -> module.thereAreUncheckedHabits(userId));
    }

    private Stream<HabitCheckerTypeModule> modules() {
        return Stream.of(CheckerType.values())
                .map(this::module)
                .filter(Objects::nonNull);
    }
}
