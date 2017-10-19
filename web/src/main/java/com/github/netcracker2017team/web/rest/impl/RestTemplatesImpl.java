package com.github.netcracker2017team.web.rest.impl;

import com.github.netcracker2017team.web.rest.api.BasicAuthTokenSupplier;
import com.github.netcracker2017team.web.rest.api.RestTemplates;
import com.github.netcracker2017team.web.security.BasicAuthToken;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alex Ivchenko
 */
public class RestTemplatesImpl implements RestTemplates {
    private final String rest;
    private final BasicAuthTokenSupplier tokenSupplier;

    public RestTemplatesImpl(String rest, BasicAuthTokenSupplier tokenSupplier) {
        this.rest = rest;
        this.tokenSupplier = tokenSupplier;
    }

    @Override
    public RestTemplate noAuth() {
        return new RestTemplateBuilder()
                .rootUri(rest)
                .build();
    }

    @Override
    public RestTemplate basicAuth() {
        BasicAuthToken token = tokenSupplier.get();
        return new RestTemplateBuilder()
                .basicAuthorization(token.username(), token.password())
                .rootUri(rest)
                .build();
    }
}
