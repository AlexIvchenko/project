package com.github.habiteria.security;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public boolean isAuthorized(Authentication auth, UUID userId) {
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

    private User object(UUID userId) {
        return userRepository.findOne(userId.toString());
    }

    private User subject(Authentication auth) {
        return  userRepository.findByUsername(auth.getName());
    }

    @Override
    public boolean isAuthorized(UUID userId, UUID habitId) {
        log.info("checking authorities: " + userId + " " + habitId);
        User user = object(userId);
        Habit habit = habitRepository.findOne(habitId.toString());
        boolean ret = habit.getOwner().equals(user);
        log.info("authorization {}", ret ? "success" : "failed");
        return ret;
    }

    @Override
    public User update(UUID id, User updated) {
        User user = userRepository.findOne(id.toString());
        user.setUsername(updated.getUsername());
        user.setEmail(updated.getEmail());
        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setPassword(passwordEncoder.encode(updated.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User patch(UUID id, User patched) {
        User user = userRepository.findOne(id.toString());
        if (patched.getUsername() != null) {
            user.setUsername(patched.getUsername());
        }
        if (patched.getEmail() != null) {
            user.setEmail(patched.getEmail());
        }
        if (patched.getFirstName() != null) {
            user.setFirstName(patched.getFirstName());
        }
        if (patched.getLastName() != null) {
            user.setLastName(patched.getLastName());
        }
        if (patched.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(patched.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public User signUp(User user) {
        log.info("signing up: " + user.toString());
        checkIdentity(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User currentUser() {
        return subject(SecurityContextHolder.getContext().getAuthentication());
    }

    private void checkIdentity(User user) {
        User loaded = userRepository.findByUsername(user.getUsername());
        if (loaded != null) {
            throw new UsernameAlreadyUsedException("username " + user.getUsername() + " already used");
        }
    }
}
