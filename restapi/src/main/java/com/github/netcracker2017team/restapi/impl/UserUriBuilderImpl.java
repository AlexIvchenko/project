package com.github.netcracker2017team.restapi.impl;

import com.github.netcracker2017team.restapi.api.UserUriBuilder;

/**
 * @author Alex Ivchenko
 */
public class UserUriBuilderImpl implements UserUriBuilder {
    private final String restRoot;

    public UserUriBuilderImpl(String restRoot) {
        this.restRoot = restRoot;
    }

    @Override
    public String get(String username) {
        return restRoot + "/user/" + username;
    }

    @Override
    public String auth() {
        return restRoot + "/auth";
    }

    @Override
    public String signUp() {
        return restRoot + "/signUp";
    }
}
