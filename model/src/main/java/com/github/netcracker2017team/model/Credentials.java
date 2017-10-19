package com.github.netcracker2017team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex Ivchenko
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class Credentials {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}