package com.github.netcracker2017team.web.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Alex Ivchenko
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
public @Data class BasicAuthToken {
    @Id
    private String username;
    private String password;
}
