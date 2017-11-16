package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public Habit create(Long userId, Habit habit) {
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
    public Habit get(Long habitId) {
        return repository.findOne(habitId);
    }
}
