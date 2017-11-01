package com.github.netcracker2017team.project.security;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Component(value = "userAuthService")
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isAuthorized(Authentication auth, String username) {
        log.info("checking authorities: " + username + " " + auth);
        return auth.getName().equals(username);
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

    private void checkIdentity(User user) {
        User loaded = userRepository.findByUsername(user.getUsername());
        if (loaded != null) {
            throw new UsernameAlreadyUsedException("username " + user.getUsername() + " already used");
        }
    }
}
