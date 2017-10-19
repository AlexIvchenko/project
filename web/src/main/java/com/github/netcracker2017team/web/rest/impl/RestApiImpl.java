package com.github.netcracker2017team.web.rest.impl;

import com.github.netcracker2017team.web.rest.api.RestApi;
import com.github.netcracker2017team.web.rest.api.UserRestApi;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alex Ivchenko
 */
public class RestApiImpl implements RestApi {
    private UserRestApi userRestApi;

    @Autowired
    public void setUserRestApi(UserRestApi userRestApi) {
        this.userRestApi = userRestApi;
    }

    @Override
    public UserRestApi user() {
        return userRestApi;
    }
}
