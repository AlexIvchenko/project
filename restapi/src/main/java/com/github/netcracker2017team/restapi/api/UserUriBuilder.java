package com.github.netcracker2017team.restapi.api;

/**
 * @author Alex Ivchenko
 */
public interface UserUriBuilder {
    String get(String username);

    String auth();

    String signUp();
}
