package com.github.habiteria.core.repository;

import com.github.habiteria.core.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface UserRepository extends Repository<User, String> {

    @Query("select u from User u where u.id = :id")
    User findOne(@Param("id") String id);

    User findByUsername(String username);

    Set<User> findByUsernameLike(@Param("username") String username);

    User save(User s);
}
