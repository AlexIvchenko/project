package com.github.netcracker2017team.rest.controller;

import lombok.*;

/**
 * @author Alex Ivchenko
 */
public @Data class Credentials {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
