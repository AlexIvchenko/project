package com.github.habiteria.security;

import com.github.habiteria.core.entities.User;
import org.springframework.security.core.Authentication;

/**
 * @author Alex Ivchenko
 */
public interface UserAuthService {
    User signUp(User user);

    User currentUser();

    boolean isAuthorized(Authentication auth, Long userId);

    boolean isAuthorized(Long userId, Long habitId);
}
