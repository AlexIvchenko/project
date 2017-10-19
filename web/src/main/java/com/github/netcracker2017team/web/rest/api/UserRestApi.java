package com.github.netcracker2017team.web.rest.api;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;

/**
 * @author Alex Ivchenko
 */
public interface UserRestApi {
    UserDto signUp(Credentials credentials);

    UserDto get(String username);

    boolean auth(String username, String password);
}
