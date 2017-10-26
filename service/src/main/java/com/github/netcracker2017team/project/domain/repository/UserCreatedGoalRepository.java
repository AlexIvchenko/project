package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.UserCreatedGoal;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface UserCreatedGoalRepository extends CrudRepository<UserCreatedGoal, String> {
    Set<UserCreatedGoal> findByOwner(User owner);
}
