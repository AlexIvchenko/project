package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Habit;
import com.github.netcracker2017team.project.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface HabitRepository extends CrudRepository<Habit, String> {
    Set<Habit> findByOwner(User owner);
}
