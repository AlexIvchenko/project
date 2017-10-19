package com.github.netcracker2017team.rest.controller;

import lombok.*;

/**
 * @author Alex Ivchenko
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public @Data class Credentials {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
