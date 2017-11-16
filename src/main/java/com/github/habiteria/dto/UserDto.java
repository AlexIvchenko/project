package com.github.habiteria.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
public class UserDto {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;
}
