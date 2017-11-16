package com.github.habiteria.security;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.builders.Users;
import com.github.habiteria.core.entities.imps.UserImpl;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service(value = "userAuthService")
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, HabitRepository habitRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isAuthorized(Authentication auth, Long userId) {
        log.info("checking authorities: " + userId + " " + auth);
        User object = object(userId);
        User subject = subject(auth);
        boolean ret = false;
        if (object != null && subject != null) {
            ret = object.equals(subject);
        }
        log.info("authorization {}", ret ? "success" : "failed");
        return ret;
    }

    private User object(Long userId) {
        return userRepository.findOne(userId);
    }

    private User subject(Authentication auth) {
        return  userRepository.findByUsername(auth.getName());
    }

    @Override
    public boolean isAuthorized(Long userId, Long habitId) {
        log.info("checking authorities: " + userId + " " + habitId);
        User user = object(userId);
        Habit habit = habitRepository.findOne(habitId);
        boolean ret = habit.getOwner().equals(user);
        log.info("authorization {}", ret ? "success" : "failed");
        return ret;
    }

    @Override
    public User signUp(UserDto dto) {
        log.info("signing up: " + dto.toString());
        checkIdentity(dto);
        UserImpl user = Users.withUsername(dto.getUsername())
                .withPassword(passwordEncoder.encode(dto.getPassword()))
                .withEmail(dto.getEmail())
                .withName(dto.getFirstName(), dto.getLastName());
        return userRepository.save(user);
    }

    @Override
    public User currentUser() {
        return subject(SecurityContextHolder.getContext().getAuthentication());
    }

    private void checkIdentity(UserDto user) {
        User loaded = userRepository.findByUsername(user.getUsername());
        if (loaded != null) {
            throw new UsernameAlreadyUsedException("username " + user.getUsername() + " already used");
        }
    }
}
