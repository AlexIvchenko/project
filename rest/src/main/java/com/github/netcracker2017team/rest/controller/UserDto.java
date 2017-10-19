package com.github.netcracker2017team.rest.controller;

import com.github.netcracker2017team.rest.model.User;
import lombok.Data;

/**
 * @author Alex Ivchenko
 */
public @Data class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public static UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.email = user.getEmail();
        return userDto;
    }
}
