package com.github.netcracker2017team.rest.security;

import org.springframework.security.core.Authentication;

/**
 * @author Alex Ivchenko
 */
public interface UserAuthService {
    boolean isAuthorized(Authentication auth, String username);
}
