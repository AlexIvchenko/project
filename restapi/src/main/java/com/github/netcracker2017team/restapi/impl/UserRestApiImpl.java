package com.github.netcracker2017team.restapi.impl;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.restapi.api.RestTemplates;
import com.github.netcracker2017team.restapi.api.UserRestApi;
import org.springframework.http.ResponseEntity;

/**
 * @author Alex Ivchenko
 */
public class UserRestApiImpl implements UserRestApi {
    private final RestTemplates templates;
    private final UriBuilder uriBuilder;

    public UserRestApiImpl(RestTemplates templates, UriBuilder uriBuilder) {
        this.templates = templates;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public UserDto signUp(Credentials credentials) {
        ResponseEntity<UserDto> response = templates.noAuth()
                .postForEntity(uriBuilder.user().signUp(), credentials, UserDto.class);
        return response.getBody();
    }

    @Override
    public UserDto get(String username) {
        ResponseEntity<UserDto> response = templates.basicAuth()
                .getForEntity(uriBuilder.user().get(username), UserDto.class);
        return response.getBody();
    }

    @Override
    public boolean auth(String username, String password) {
        ResponseEntity<Boolean> response = templates.basicAuth(username, password)
                .getForEntity(uriBuilder.user().auth(), Boolean.class);
        return response.getBody();
    }
}
