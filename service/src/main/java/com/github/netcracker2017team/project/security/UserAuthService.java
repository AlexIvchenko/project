package com.github.netcracker2017team.project.security;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.project.domain.model.User;
import org.springframework.security.core.Authentication;

/**
 * @author Alex Ivchenko
 */
public interface UserAuthService {
    User signUp(Credentials credentials);
    boolean isAuthorized(Authentication auth, String username);
}
