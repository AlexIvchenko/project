package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitServiceImpl implements HabitService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public HabitServiceImpl(UserRepository userRepository, HabitRepository habitRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public Set<Habit> getDailyHabits(UUID userId) {
        User owner = userRepository.findOne(userId.toString());
        return habitRepository.findByOwner(owner);
    }

    @Override
    public Habit getDailyHabit(UUID userId, UUID habitId) {
        return habitRepository.findOne(habitId.toString());
    }

    @Override
    public Habit perform(UUID userId, UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        // TODO make and save result
        return habit;
    }

    @Override
    public Habit fail(UUID userId, UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        // TODO make and save result
        return habit;
    }

    @Override
    public Habit createDailyHabit(UUID userId, Habit habit) {
        User owner = userRepository.findOne(userId.toString());
        habit.setOwner(owner);
        return habitRepository.save(habit);
    }

    @Override
    public boolean isAvailableToDoToday(Habit habit) {
        // TODO implement logic
        return true;
    }
}
