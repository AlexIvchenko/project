package com.github.habiteria.core.repository;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Result;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface ResultRepository extends CrudRepository<Result, String> {
    Set<Result> findByHabit(Habit habit);

    int countByHabitAndDate(Habit habit, LocalDate date);

    Set<Result> findByDate(LocalDate date);

    Result findByHabitAndDate(Habit habit, LocalDate date);
}
