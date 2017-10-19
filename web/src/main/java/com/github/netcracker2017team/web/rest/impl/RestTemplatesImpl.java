package com.github.netcracker2017team.web.rest.impl;

import com.github.netcracker2017team.web.rest.api.RestTemplates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alex Ivchenko
 */
public class RestTemplatesImpl implements RestTemplates {
    @Value("${REST_ROOT}")
    private String rest;

    @Override
    public RestTemplate noAuth() {
        return new RestTemplateBuilder()
                .rootUri(rest)
                .build();
    }

    @Override
    public RestTemplate basicAuth(String username, String password) {
        return new RestTemplateBuilder()
                .basicAuthorization(username, password)
                .rootUri(rest)
                .build();
    }
}
