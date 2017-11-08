package com.github.habitaria.restapi.api.uri;

/**
 * @author Alex Ivchenko
 */
public interface UserUriBuilder {
    String get(String username);

    String auth();

    String signUp();
}
