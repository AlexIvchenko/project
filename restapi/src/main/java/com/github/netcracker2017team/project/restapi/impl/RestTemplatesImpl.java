package com.github.netcracker2017team.project.restapi.impl;

import com.github.netcracker2017team.project.restapi.api.BasicAuthToken;
import com.github.netcracker2017team.project.restapi.api.BasicAuthTokenSupplier;
import com.github.netcracker2017team.project.restapi.api.RestTemplates;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alex Ivchenko
 */
public class RestTemplatesImpl implements RestTemplates {
    private final BasicAuthTokenSupplier tokenSupplier;

    public RestTemplatesImpl(BasicAuthTokenSupplier tokenSupplier) {
        this.tokenSupplier = tokenSupplier;
    }

    @Override
    public RestTemplate noAuth() {
        return new RestTemplate();
    }

    @Override
    public RestTemplate basicAuth() {
        BasicAuthToken token = tokenSupplier.get();
        return basicAuth(token);
    }

    @Override
    public RestTemplate basicAuth(String username, String password) {
        return basicAuth(new BasicAuthToken(username, password));
    }

    private RestTemplate basicAuth(BasicAuthToken token) {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new BasicAuthorizationInterceptor(token.username(), token.password()));
        return template;
    }
}
