package com.github.netcracker2017team.rest.service;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.rest.model.User;

/**
 * @author Alex Ivchenko
 */
public interface UserService {
    User signUp(Credentials credentials);
    User getByUsername(String username);
}
