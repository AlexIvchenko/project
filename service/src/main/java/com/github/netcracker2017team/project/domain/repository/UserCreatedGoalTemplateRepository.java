package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.UserCreatedGoalTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface UserCreatedGoalTemplateRepository extends CrudRepository<UserCreatedGoalTemplate, String> {
    Set<UserCreatedGoalTemplate> findByOwner(User owner);
}
