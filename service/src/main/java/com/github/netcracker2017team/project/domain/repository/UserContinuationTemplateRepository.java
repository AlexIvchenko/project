package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.UserContinuationTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@RepositoryRestResource(exported = false)
public interface UserContinuationTemplateRepository extends CrudRepository<UserContinuationTemplate, String> {
    Set<UserContinuationTemplate> findByOwner(User owner);
}
