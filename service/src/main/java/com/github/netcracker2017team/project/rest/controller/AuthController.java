package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.project.rest.controller.converter.UserDtoConverter;
import com.github.netcracker2017team.project.security.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserAuthService userAuthService;
    private final UserDtoConverter converter = new UserDtoConverter();

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody final Credentials credentials) {
        log.info("signing up: " + credentials);
        return converter.toDto(userAuthService.signUp(credentials));
    }
}
