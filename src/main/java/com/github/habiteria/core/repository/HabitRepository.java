package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.HabitImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface HabitRepository extends CrudRepository<HabitImpl, Long> {
    Set<Habit> findByOwner(User owner);
}
