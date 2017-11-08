package com.github.habiteria.domain.repository;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.model.Result;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface ResultRepository extends CrudRepository<Result, String> {
    Set<Result> findByHabit(Habit habit);
    int countByHabitAndDate(Habit habit, LocalDate date);
}
