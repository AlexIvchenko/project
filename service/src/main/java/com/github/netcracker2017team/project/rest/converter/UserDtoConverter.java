package com.github.netcracker2017team.project.rest.converter;

import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.project.domain.model.User;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public class UserDtoConverter {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(UUID.fromString(user.getId()))
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User fromDto(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
        user.setId(userDto.getId().toString());
        return user;
    }
}
