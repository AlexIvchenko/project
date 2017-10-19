package com.github.netcracker2017team.web.rest.impl;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.web.rest.api.RestTemplates;
import com.github.netcracker2017team.web.rest.api.UserRestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class UserRestApiImpl implements UserRestApi {
    private final RestTemplates templates;

    public UserRestApiImpl(RestTemplates templates) {
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
        ResponseEntity<UserDto> response = templates.basicAuth()
                .getForEntity(getUserUrl(username), UserDto.class);
        return response.getBody();
    }

    @Override
    public boolean auth(String username, String password) {
        ResponseEntity<Boolean> response = templates.basicAuth()
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
