package com.github.habiteria.core.domain.result;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Result;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final HabitRepository habitRepository;

    public ResultServiceImpl(ResultRepository resultRepository, HabitRepository habitRepository) {
        this.resultRepository = resultRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public Set<Result> getResults(UUID userId, UUID habitId) {
        Habit habit = habitRepository.findOne(habitId.toString());
        return resultRepository.findByHabit(habit);
    }

    @Override
    public Set<Result> getResults(UUID userId, LocalDate date) {
        return resultRepository.findByDate(date);
    }
}
