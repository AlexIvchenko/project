package com.github.habiteria.core.domain.habit.tracking;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.domain.habit.tracking.module.ScheduleModules;
import com.github.habiteria.core.domain.habit.tracking.module.TrackingModule;
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
public class HabitTrackingServiceImpl implements HabitTrackingService {
    private final HabitRepository habitRepository;
    private final ScheduleModules modules;

    public HabitTrackingServiceImpl(HabitRepository habitRepository, ScheduleModules modules) {
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

    private TrackingModule getModule(UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        return getModule(habit);
    }

    private TrackingModule getModule(Habit habit) {
        return modules.get(habit.getSchedule().getType());
    }
}
