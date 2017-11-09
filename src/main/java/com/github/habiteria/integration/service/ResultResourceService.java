package com.github.habiteria.integration.service;

import com.github.habiteria.integration.resources.ResultsResource;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface ResultResourceService {
    ResultsResource getResults(UUID userId, UUID habitId);

    ResultsResource getResults(UUID userId, LocalDate date);
}
