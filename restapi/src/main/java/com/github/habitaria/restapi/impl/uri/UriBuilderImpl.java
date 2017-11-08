package com.github.habitaria.restapi.impl.uri;

import com.github.habitaria.restapi.api.uri.UriBuilder;
import com.github.habitaria.restapi.api.uri.UserUriBuilder;

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
