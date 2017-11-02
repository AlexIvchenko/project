package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface UserGoalTemplateRepository extends CrudRepository<UserGoalTemplate, String> {
    Set<UserGoalTemplate> findByOwner(User owner);
}
