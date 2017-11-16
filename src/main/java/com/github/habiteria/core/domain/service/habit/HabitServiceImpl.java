package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.builders.Habits;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.HabitImpl;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.dto.HabitDto;
import org.springframework.stereotype.Service;

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
    public Habit create(Long userId, HabitDto dto) {
        User user = userRepository.findOne(userId);
        HabitImpl habit = Habits.withOwner(user)
                .withName(dto.getName())
                .withDescription(dto.getDescription())
                .withStart(dto.getSchedule().getStart());

        return repository.save(habit);
    }

    @Override
    public Habit get(Long habitId) {
        return repository.findOne(habitId);
    }
}
