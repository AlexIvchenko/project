package com.github.netcracker2017team.rest.controller;

import com.github.netcracker2017team.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Ivchenko
 */
@RestController("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody final Credentials credentials) {
        return UserDto.convert(userService.signUp(credentials));
    }

    @GetMapping("/user/{username}")
    public UserDto get(@PathVariable("username") final String username) {
        return UserDto.convert(userService.getByUsername(username));
    }
}
