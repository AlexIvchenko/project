package com.github.habiteria.integration.controller;

import com.github.habiteria.dto.UserDto;
import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.assemblers.UserResAsm;
import com.github.habiteria.integration.domain.resources.UserResource;
import com.github.habiteria.security.service.UserAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author Alex Ivchenko
 */
@Rest
public class AuthController {
    private final UserAuthService userAuthService;
    private final UserResAsm assembler;

    public AuthController(UserAuthService userAuthService, UserResAsm assembler) {
        this.userAuthService = userAuthService;
        this.assembler = assembler;
    }

    @PostMapping(path = "/users")
    public HttpEntity<UserResource> create(@RequestBody @Valid final UserDto user) {
        return new HttpEntity<>(assembler.toResource(userAuthService.signUp(user)));
    }
}
