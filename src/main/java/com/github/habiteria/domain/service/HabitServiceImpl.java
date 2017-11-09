package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

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
    public Habit createHabit(UUID userId, Habit habit) {
        User user = userRepository.findOne(userId.toString());
        habit.setOwner(user);
        return habitRepository.save(habit);
    }
}
