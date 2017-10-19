package com.github.netcracker2017team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex Ivchenko
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
