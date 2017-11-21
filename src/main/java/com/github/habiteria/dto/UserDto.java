package com.github.habiteria.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
public class UserDto {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;
}
