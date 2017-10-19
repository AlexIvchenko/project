package com.github.netcracker2017team.web.rest;

import com.github.netcracker2017team.web.rest.api.RestApi;
import com.github.netcracker2017team.web.rest.api.RestTemplates;
import com.github.netcracker2017team.web.rest.api.UserRestApi;
import com.github.netcracker2017team.web.rest.impl.RestApiImpl;
import com.github.netcracker2017team.web.rest.impl.RestTemplatesImpl;
import com.github.netcracker2017team.web.rest.impl.UserRestApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class RestConfig {
    @Bean
    public RestApi restApi() {
        return new RestApiImpl();
    }

    @Bean
    public UserRestApi userRestApi() {
        return new UserRestApiImpl();
    }

    @Bean
    public RestTemplates restTemplates() {
        return new RestTemplatesImpl();
    }
}
