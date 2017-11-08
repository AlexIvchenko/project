package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.rest.Rest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Rest
public class RootController {
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<ResourceSupport> api() {
        ResourceSupport links = new ResourceSupport();
        links.add(linkTo(methodOn(AuthController.class).create(null)).withRel("signUp"));
        return new HttpEntity<>(links);
    }
}
