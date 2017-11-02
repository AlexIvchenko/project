package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.security.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Rest
public class AuthController {
    private final UserAuthService userAuthService;
    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/users/")
    @ResponseBody
    public PersistentEntityResource create(@RequestBody final User user,
                                           PersistentEntityResourceAssembler asm) {
        log.info("signing up: " + user);
        return asm.toFullResource(userAuthService.signUp(user));
    }

    @PutMapping("/users/{id}")
    @ResponseBody
    public PersistentEntityResource update(@PathVariable final UUID id,
                                           @RequestBody final User user,
                                           PersistentEntityResourceAssembler asm) {
        log.info("updating up: " + user);
        return asm.toFullResource(userAuthService.update(id, user));
    }

    @PatchMapping("/users/{id}")
    @ResponseBody
    public PersistentEntityResource patch(@PathVariable final UUID id,
                                          @RequestBody final User user,
                                          PersistentEntityResourceAssembler asm) {
        log.info("patching doer: " + user);
        return asm.toFullResource(userAuthService.patch(id, user));
    }
}
