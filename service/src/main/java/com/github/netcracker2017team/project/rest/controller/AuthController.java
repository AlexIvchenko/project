package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.security.UserAuthService;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@RepositoryRestController
@RequestMapping("/api/")
public class AuthController {
    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping(path = "/users", produces = "application/hal+json")
    @ResponseBody
    public PersistentEntityResource create(@RequestBody final User user,
                                           PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(userAuthService.signUp(user));
    }

    @RequestMapping(path = "/users", method = RequestMethod.HEAD)
    @ResponseBody
    public void createHead() {

    }

    @PutMapping(path = "/users/{id}", produces = "application/hal+json")
    @ResponseBody
    public PersistentEntityResource update(@PathVariable final UUID id,
                                           @RequestBody final User user,
                                           PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(userAuthService.update(id, user));
    }

    @PatchMapping(path = "/users/{id}", produces = "application/hal+json")
    @ResponseBody
    public PersistentEntityResource patch(@PathVariable final UUID id,
                                          @RequestBody final User user,
                                          PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(userAuthService.patch(id, user));
    }
}
