package com.github.netcracker2017team.restapi.impl;

import com.github.netcracker2017team.restapi.api.UserUriBuilder;

/**
 * @author Alex Ivchenko
 */
public class UriBuilder {
    private final String restRoot;

    public UriBuilder(String restRoot) {
        this.restRoot = restRoot;
    }

    public UserUriBuilder user() {
        return new UserUriBuilderImpl(restRoot);
    }
}
