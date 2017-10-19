package com.github.netcracker2017team.rest.controller;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.rest.controller.converter.UserDtoConverter;
import com.github.netcracker2017team.rest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Ivchenko
 */
@RestController
@Slf4j
public class UserController {
    private UserService userService;
    private UserDtoConverter converter = new UserDtoConverter();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody final Credentials credentials) {
        return converter.toDto(userService.signUp(credentials));
    }

    @GetMapping("/user/{username}")
    public UserDto get(@PathVariable("username") final String username) {
        return converter.toDto(userService.getByUsername(username));
    }

    @GetMapping("/auth")
    public Boolean auth() {
        return true;
    }
}
