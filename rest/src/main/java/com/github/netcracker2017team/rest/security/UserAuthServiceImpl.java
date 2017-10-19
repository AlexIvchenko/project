package com.github.netcracker2017team.rest.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component(value = "userAuthService")
public class UserAuthServiceImpl implements UserAuthService {
    public boolean isAuthorized(Authentication auth, String username) {
        return auth.getName().equals(username);
    }
}
