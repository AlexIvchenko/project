package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.User;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitServiceImpl implements HabitService {
    private final UserRepository userRepository;
    private final HabitRepository repository;
    private final Scheduler scheduler;

    public HabitServiceImpl(UserRepository userRepository, HabitRepository repository, Scheduler scheduler) {
        this.userRepository = userRepository;
        this.repository = repository;
        this.scheduler = scheduler;
    }

    @Override
    public Habit create(UUID userId, Habit habit) {
        User user = userRepository.findOne(userId.toString());
        habit.setOwner(user);
        habit = repository.save(habit);
        scheduler.generate(habit, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        return habit;
    }
}
