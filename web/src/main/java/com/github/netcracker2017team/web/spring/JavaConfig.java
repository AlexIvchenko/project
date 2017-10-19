package com.github.netcracker2017team.web.spring;

import com.github.netcracker2017team.web.repository.UserDtoRepository;
import com.github.netcracker2017team.web.repository.UserDtoRepositoryImpl;
import com.github.netcracker2017team.web.service.UserService;
import com.github.netcracker2017team.web.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class JavaConfig {
    @Bean
    public UserDtoRepository userDtoRepository() {
        return new UserDtoRepositoryImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
