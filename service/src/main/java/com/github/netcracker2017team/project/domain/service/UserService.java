package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.User;

/**
 * @author Alex Ivchenko
 */
public interface UserService {
    User getByUsername(String username);
}
