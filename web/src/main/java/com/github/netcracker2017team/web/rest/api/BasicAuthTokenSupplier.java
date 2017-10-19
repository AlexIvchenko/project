package com.github.netcracker2017team.web.rest.api;

import com.github.netcracker2017team.web.security.BasicAuthToken;

/**
 * @author Alex Ivchenko
 */
@FunctionalInterface
public interface BasicAuthTokenSupplier {
    BasicAuthToken get();
}
