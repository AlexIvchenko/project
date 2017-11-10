package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.domain.service.RootResourceService;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Alex Ivchenko
 */
@Rest
public class RootController {
    private final RootResourceService service;

    public RootController(RootResourceService service) {
        this.service = service;
    }

    @GetMapping
    public HttpEntity<ResourceSupport> api() {
        return new HttpEntity<>(service.availableActions());
    }
}
