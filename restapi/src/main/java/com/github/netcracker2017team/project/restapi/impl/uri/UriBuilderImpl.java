package com.github.netcracker2017team.project.restapi.impl.uri;

import com.github.netcracker2017team.project.restapi.api.uri.UriBuilder;
import com.github.netcracker2017team.project.restapi.api.uri.UserUriBuilder;

/**
 * @author Alex Ivchenko
 */
public class UriBuilderImpl implements UriBuilder {
    private final String restRoot;

    public UriBuilderImpl(String restRoot) {
        this.restRoot = restRoot;
    }

    @Override
    public UserUriBuilder user() {
        return new UserUriBuilderImpl(restRoot);
    }
}
