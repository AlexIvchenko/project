package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.resources.ResultsResource;
import com.github.habiteria.integration.service.ResultResourceService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Rest
public class ResultsController {
    private final ResultResourceService service;

    public ResultsController(ResultResourceService service) {
        this.service = service;
    }

    @GetMapping(path = "/users/{userId}/habits/{habitId}/results")
    public HttpEntity<ResultsResource> getResults(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId) {
        return new HttpEntity<>(service.getResults(userId, habitId));
    }
}
