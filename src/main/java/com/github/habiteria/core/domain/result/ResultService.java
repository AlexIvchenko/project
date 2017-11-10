package com.github.habiteria.core.domain.result;

import com.github.habiteria.core.model.Result;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface ResultService {
    Set<Result> getResults(UUID userId, UUID habitId);

    Set<Result> getResults(UUID userId, LocalDate date);
}
