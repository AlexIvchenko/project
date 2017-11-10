package com.github.habiteria.domain.service.habit.core;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.service.habit.core.module.ScheduleModules;
import com.github.habiteria.domain.service.habit.core.module.ScheduleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Primary
@Service("habitSnapshotService")
public class HabitSnapshotServiceImpl implements HabitSnapshotService {
    private final HabitRepository habitRepository;
    private final ScheduleModules modules;

    public HabitSnapshotServiceImpl(HabitRepository habitRepository, ScheduleModules modules) {
        this.habitRepository = habitRepository;
        this.modules = modules;
    }

    @Override
    public HabitSnapshot createHabit(UUID userId, Habit habit) {
        return getModule(habit).createHabit(userId, habit);
    }

    @Override
    public HabitSnapshot getHabit(UUID userId, UUID habitId, LocalDate date) {
        return getModule(habitId).getHabit(userId, habitId, date);
    }

    @Override
    public Set<HabitSnapshot> getHabits(UUID userId, LocalDate date) {
        return modules.all()
                .map(module -> module.getHabits(userId, date))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public HabitSnapshot performHabit(UUID userId, UUID habitId, LocalDate date) {
        return getModule(habitId).performHabit(userId, habitId, date);
    }

    @Override
    public HabitSnapshot failHabit(UUID userId, UUID habitId, LocalDate date) {
        return getModule(habitId).failHabit(userId, habitId, date);
    }

    @Override
    public HabitSnapshot undoHabit(UUID userId, UUID habitId, LocalDate date) {
        return getModule(habitId).undoHabit(userId, habitId, date);
    }

    private ScheduleModule getModule(UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        return getModule(habit);
    }

    private ScheduleModule getModule(Habit habit) {
        return modules.get(habit.getSchedule().getType());
    }
}
