package com.github.netcracker2017team.project.domain.repository;

import com.github.netcracker2017team.project.domain.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@RepositoryRestController
public interface UserRepository extends Repository<User, String> {

    @Query("select u from User u where u.id = :id")
    User findOne(@Param("id") String id);

    @RestResource
    User findByUsername(String username);

    @RestResource(path = "username")
    Set<User> findByUsernameLike(@Param("username") String username);

    @RestResource(exported = false)
    User save(User s);
}
