package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.personal.PersonalGoal;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface PersonalGoalRepository extends CrudRepository<PersonalGoal, String> {
    Set<PersonalGoal> findByDoerAndStatus(User doer, Goal.Status status);
}
