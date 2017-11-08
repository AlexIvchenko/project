package com.github.habiteria.domain.repository;

import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface HabitRepository extends CrudRepository<Habit, String> {
    Set<Habit> findByOwner(User owner);
}
