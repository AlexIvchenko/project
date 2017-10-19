package com.github.netcracker2017team.restapi.builder;

import com.github.netcracker2017team.restapi.api.BasicAuthTokenSupplier;
import com.github.netcracker2017team.restapi.api.RestApi;
import com.github.netcracker2017team.restapi.impl.RestApiImpl;
import com.github.netcracker2017team.restapi.impl.RestTemplatesImpl;
import com.github.netcracker2017team.restapi.impl.UriBuilder;
import com.github.netcracker2017team.restapi.impl.UserRestApiImpl;

/**
 * @author Alex Ivchenko
 */
public class RestApiBuilder {
    private String restRoot;
    private BasicAuthTokenSupplier tokenSupplier;

    public RestApiBuilder root(String restRoot) {
        this.restRoot = restRoot;
        return this;
    }

    public RestApiBuilder tokens(BasicAuthTokenSupplier tokenSupplier) {
        this.tokenSupplier = tokenSupplier;
        return this;
    }

    public RestApi build() {
        return new RestApiImpl(new UserRestApiImpl(new RestTemplatesImpl(tokenSupplier), new UriBuilder(restRoot)));
    }
}
