package com.github.habiteria.security.service;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.dto.UserDto;
import org.springframework.security.core.Authentication;

/**
 * @author Alex Ivchenko
 */
public interface UserAuthService {
    User signUp(UserDto dto);

    User currentUser();

    boolean isAuthorized(Authentication auth, Long userId);

    boolean isAuthorized(Long userId, Long habitId);
}
