package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.project.rest.controller.converter.UserDtoConverter;
import com.github.netcracker2017team.project.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@RestController()
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private UserDtoConverter converter = new UserDtoConverter();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public UserDto get(@PathVariable("username") final String username) {
        log.info("getting user: " + username);
        return converter.toDto(userService.getByUsername(username));
    }
}
