package com.github.habiteria.integration.controller;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.assemblers.UserResAsm;
import com.github.habiteria.integration.domain.resources.UserResource;
import com.github.habiteria.security.UserAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public HttpEntity<UserResource> create(@RequestBody final User user) {
        return new HttpEntity<>(assembler.toResource(userAuthService.signUp(user)));
    }

    @RequestMapping(path = "/users", method = RequestMethod.HEAD)
    public void createHead() {

    }

    @PutMapping(path = "/users/{id}")
    public HttpEntity<UserResource> update(@PathVariable final UUID id,
                                           @RequestBody final User user) {
        return new HttpEntity<>(assembler.toResource(userAuthService.update(id, user)));
    }

    @PatchMapping(path = "/users/{id}")
    public HttpEntity<UserResource> patch(@PathVariable final UUID id,
                                          @RequestBody final User user) {
        return new HttpEntity<>(assembler.toResource(userAuthService.patch(id, user)));
    }
}
