package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Schedule;
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

    public HabitServiceImpl(UserRepository userRepository, HabitRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @Override
    public Habit create(UUID userId, Habit habit) {
        User user = userRepository.findOne(userId.toString());
        Schedule schedule = habit.getSchedule();
        if (schedule == null) {
            schedule = new Schedule();
        }
        if (schedule.getStart() == null) {
            schedule.setStart(LocalDateTime.now());
        }
        habit.setOwner(user);
        habit = repository.save(habit);
        return habit;
    }

    @Override
    public Habit get(UUID habitId) {
        return repository.findOne(habitId.toString());
    }
}
