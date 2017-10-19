package com.github.netcracker2017team.web.rest;

import com.github.netcracker2017team.restapi.api.RestApi;
import com.github.netcracker2017team.restapi.builder.RestApiBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class RestConfig {
    @Value("${REST_ROOT}")
    private String rest;

    @Bean
    public RestApi restApi() {
        return new RestApiBuilder()
                .root(rest)
                .tokens(new BasicAuthTokenSupplierImpl())
                .build();
    }
}
