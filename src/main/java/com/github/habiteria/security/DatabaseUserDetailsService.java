package com.github.habiteria.security;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.core.domain.service.visitor.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class DatabaseUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private VisitorService visitorService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setVisitorService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loading user " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ username);
        }
        visitorService.visit(user);
        return  org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
