package com.github.netcracker2017team.project.security;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
    public User signUp(Credentials credentials) {
        log.info("signing up" + credentials.toString());
        checkIdentity(credentials);
        User user = User.builder()
                .username(credentials.getUsername())
                .email(credentials.getEmail())
                .firstName(credentials.getFirstName())
                .lastName(credentials.getLastName())
                .password(passwordEncoder.encode(credentials.getPassword()))
                .build();
        return userRepository.save(user);
    }

    private void checkIdentity(Credentials credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user != null) {
            throw new UsernameAlreadyUsed("username " + credentials.getUsername() + " already used");
        }
    }
}
