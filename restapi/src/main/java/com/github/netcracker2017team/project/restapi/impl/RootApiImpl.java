package com.github.netcracker2017team.project.restapi.impl;

import com.github.netcracker2017team.project.restapi.api.RootApi;
import com.github.netcracker2017team.project.restapi.api.UserRestApi;

/**
 * @author Alex Ivchenko
 */
public class RootApiImpl implements RootApi {
    private final UserRestApi userRestApi;

    public RootApiImpl(UserRestApi userRestApi) {
        this.userRestApi = userRestApi;
    }

    @Override
    public UserRestApi user() {
        return userRestApi;
    }
}
