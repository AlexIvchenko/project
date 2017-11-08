package com.github.habitaria.restapi.api;

/**
 * @author Alex Ivchenko
 */
@FunctionalInterface
public interface BasicAuthTokenSupplier {
    BasicAuthToken get();
}
