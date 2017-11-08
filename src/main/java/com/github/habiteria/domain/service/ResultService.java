package com.github.habiteria.domain.service;

import com.github.habiteria.domain.model.Result;

import java.util.Set;
import java.util.UUID; /**
 * @author Alex Ivchenko
 */
public interface ResultService {
    Set<Result> getResults(UUID userId, UUID habitId);
}
