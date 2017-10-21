package com.github.netcracker2017team.project.restapi.api;

/**
 * @author Alex Ivchenko
 */
@FunctionalInterface
public interface BasicAuthTokenSupplier {
    BasicAuthToken get();
}
