package com.github.netcracker2017team.web.rest.impl;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.web.rest.api.RestTemplates;
import com.github.netcracker2017team.web.rest.api.UserRestApi;
import com.github.netcracker2017team.web.security.BasicAuthToken;
import com.github.netcracker2017team.web.security.BasicAuthTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class UserRestApiImpl implements UserRestApi {
    private RestTemplates templates;
    private BasicAuthTokenRepository basicAuthTokenRepository;

    @Autowired
    public void setBasicAuthTokenRepository(BasicAuthTokenRepository basicAuthTokenRepository) {
        this.basicAuthTokenRepository = basicAuthTokenRepository;
    }

    @Autowired
    public void setTemplates(RestTemplates templates) {
        this.templates = templates;
    }

    @Override
    public UserDto signUp(Credentials credentials) {
        ResponseEntity<UserDto> response = templates.noAuth()
                .postForEntity(signUpUrl(), credentials, UserDto.class);
        return response.getBody();
    }

    @Override
    public UserDto get(String username) {
        BasicAuthToken token = basicAuthTokenRepository.findOne(username);
        ResponseEntity<UserDto> response = templates.basicAuth(token.getUsername(), token.getPassword())
                .getForEntity(getUserUrl(username), UserDto.class);
        return response.getBody();
    }

    @Override
    public boolean auth(String username, String password) {
        ResponseEntity<Boolean> response = templates.basicAuth(username, password)
                .getForEntity(authUrl(), Boolean.class);
        return response.getBody();
    }

    private String authUrl() {
        return "/auth";
    }

    private String signUpUrl() {
        return "/signUp";
    }

    private String getUserUrl(String username) {
        return "/user/" + username;
    }
}
