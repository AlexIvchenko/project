package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.domain.resources.ResultResource;
import com.github.habiteria.integration.domain.service.ResultResourceService;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
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
    public HttpEntity<Resources<ResultResource>> getResults(
            @PathVariable("userId") final UUID userId,
            @PathVariable("habitId") final UUID habitId) {
        return new HttpEntity<>(service.getResults(userId, habitId));
    }

    @GetMapping(path = "/users/{userId}/habits/results/{date}")
    public HttpEntity<Resources<ResultResource>> getResults(
            @PathVariable("userId") final UUID userId,
            @PathVariable("date") final LocalDate date) {
        return new HttpEntity<>(service.getResults(userId, date));
    }
}
