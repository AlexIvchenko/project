package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.assembler.UserResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.UserResource;
import com.github.netcracker2017team.project.security.UserAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Rest
public class AuthController {
    private final UserAuthService userAuthService;
    private final UserResourceAssembler assembler;

    public AuthController(UserAuthService userAuthService, UserResourceAssembler assembler) {
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
