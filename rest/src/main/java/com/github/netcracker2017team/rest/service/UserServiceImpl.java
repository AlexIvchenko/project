package com.github.netcracker2017team.rest.service;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.rest.model.User;
import com.github.netcracker2017team.rest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signUp(Credentials credentials) {
        log.info(credentials.toString());
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

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
