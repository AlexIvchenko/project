package com.github.habiteria.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
public class UserDto {
    @NotNull(message = "{UserDto.username.notEmpty}")
    private String username;

    @NotNull(message = "{UserDto.password.notEmpty}")
    private String password;

    @NotNull(message = "{UserDto.email.notEmpty}")
    private String email;

    private String firstName;

    private String lastName;
}
