package com.github.habitaria.restapi.impl;

import com.github.habitaria.restapi.api.RootApi;
import com.github.habitaria.restapi.api.UserRestApi;

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
