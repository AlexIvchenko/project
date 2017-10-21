package com.github.netcracker2017team.project.restapi.impl.uri;

import com.github.netcracker2017team.project.restapi.api.uri.UserUriBuilder;

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
