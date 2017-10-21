package com.github.netcracker2017team.project.restapi.api.uri;

/**
 * @author Alex Ivchenko
 */
public interface UserUriBuilder {
    String get(String username);

    String auth();

    String signUp();
}
