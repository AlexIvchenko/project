package com.github.netcracker2017team.web.repository;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;

/**
 * @author Alex Ivchenko
 */
public interface UserDtoRepository {
    UserDto findByUsername(String username);
    UserDto save(Credentials credentials);
}
