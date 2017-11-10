package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.ResultResource;
import org.springframework.hateoas.Resources;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface ResultResourceService {
    Resources<ResultResource> getResults(UUID userId, UUID habitId);

    Resources<ResultResource> getResults(UUID userId, LocalDate date);
}
