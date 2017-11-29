package com.github.habiteria.security;

import com.github.habiteria.core.domain.service.visitor.VisitorService;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Alex Ivchenko
 */
@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final VisitorService visitorService;

    public DatabaseUserDetailsService(UserRepository userRepository, VisitorService visitorService) {
        this.userRepository = userRepository;
        this.visitorService = visitorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
