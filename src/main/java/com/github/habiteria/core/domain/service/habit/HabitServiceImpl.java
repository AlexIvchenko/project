package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.service.fetcher.StrictFetcher;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.builders.Habits;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.HabitImpl;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.dto.HabitDto;
import com.github.habiteria.dto.PatchHabitDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitServiceImpl implements HabitService {
    private final UserRepository userRepository;
    private final StrictFetcher fetcher;
    private final HabitRepository repository;

    public HabitServiceImpl(UserRepository userRepository, StrictFetcher fetcher, HabitRepository repository) {
        this.userRepository = userRepository;
        this.fetcher = fetcher;
        this.repository = repository;
    }

    @Override
    public Habit create(Long userId, HabitDto dto) {
        User user = userRepository.findOne(userId);

        LocalDateTime start;
        if (dto.getSchedule().getStart().equals(LocalDate.now())) {
            start = LocalDateTime.now();
        } else {
            start = LocalDateTime.of(dto.getSchedule().getStart(), LocalTime.MIN);
        }

        LocalDateTime end = LocalDateTime.of(dto.getSchedule().getEnd(), LocalTime.MAX);

        HabitImpl habit = Habits.withOwner(user)
                .withName(dto.getName())
                .withDescription(dto.getDescription())
                .withStart(start)
                .withEnd(end)
                .withType(dto.getSchedule().getType());

        return repository.save(habit);
    }

    @Override
    public Habit get(Long habitId) {
        return fetcher.fetchHabit(habitId);
    }

    @Override
    public Set<Habit> getHabits(Long userId) {
        User user = fetcher.fetchUser(userId);
        return repository.findByOwner(user);
    }

    @Override
    public Habit patch(Long habitId, PatchHabitDto patch) {
        HabitImpl habit = repository.findOne(habitId);
        if (patch.getName() != null) {
            habit.setName(patch.getName());
        }
        if (patch.getDescription() != null) {
            habit.setDescription(patch.getDescription());
        }
        return repository.save(habit);
    }
}
