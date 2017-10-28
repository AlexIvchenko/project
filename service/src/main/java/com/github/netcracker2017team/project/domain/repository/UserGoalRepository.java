package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.UserGoal;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface UserGoalRepository extends CrudRepository<UserGoal, String> {
    Set<UserGoal> findByOwner(User owner);
}
