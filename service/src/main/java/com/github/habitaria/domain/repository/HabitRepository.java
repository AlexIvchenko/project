package com.github.habitaria.domain.repository;

import com.github.habitaria.domain.model.Habit;
import com.github.habitaria.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface HabitRepository extends CrudRepository<Habit, String> {
    Set<Habit> findByOwner(User owner);
}
