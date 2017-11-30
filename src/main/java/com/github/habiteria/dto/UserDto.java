package com.github.habiteria.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
public class UserDto {
    @NotNull(message = "{UserDto.username.notEmpty}")
    @Size(min = 1, message = "{UserDto.username.notEmpty}")
    private String username;

    @NotNull(message = "{UserDto.password.notEmpty}")
    @Size(min = 1, message = "{UserDto.password.notEmpty}")
    private String password;

    @NotNull(message = "{UserDto.email.notEmpty}")
    @Size(min = 1, message = "{UserDto.email.notEmpty}")
    private String email;

    private String firstName;

    private String lastName;
}
