package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@RepositoryRestController
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

    @RestResource(path = "username")
    Set<User> findByUsernameLike(@Param("username") String username);
}
