package com.github.netcracker2017team.project.restapi.api;

/**
 * @author Alex Ivchenko
 */
public class BasicAuthToken {
    private final String username;
    private final String password;

    public BasicAuthToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
