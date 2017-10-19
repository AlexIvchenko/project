package com.github.netcracker2017team.restapi.api;

/**
 * @author Alex Ivchenko
 */
@FunctionalInterface
public interface BasicAuthTokenSupplier {
    BasicAuthToken get();
}
