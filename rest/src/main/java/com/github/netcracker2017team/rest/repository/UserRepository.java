package com.github.netcracker2017team.rest.repository;

import com.github.netcracker2017team.rest.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
