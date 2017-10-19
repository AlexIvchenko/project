package com.github.netcracker2017team.restapi.impl;

import com.github.netcracker2017team.restapi.api.RestApi;
import com.github.netcracker2017team.restapi.api.UserRestApi;

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
