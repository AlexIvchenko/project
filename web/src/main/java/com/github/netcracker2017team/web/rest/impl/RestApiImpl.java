package com.github.netcracker2017team.web.rest.impl;

import com.github.netcracker2017team.web.rest.api.RestApi;
import com.github.netcracker2017team.web.rest.api.UserRestApi;

/**
 * @author Alex Ivchenko
 */
public class RestApiImpl implements RestApi {
    private final UserRestApi userRestApi;

    public RestApiImpl(UserRestApi userRestApi) {
        this.userRestApi = userRestApi;
    }

    @Override
    public UserRestApi user() {
        return userRestApi;
    }
}
