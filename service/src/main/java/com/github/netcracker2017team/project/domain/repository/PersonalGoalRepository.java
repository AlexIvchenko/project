package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.PersonalGoal;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface PersonalGoalRepository extends CrudRepository<PersonalGoal, String> {
}
