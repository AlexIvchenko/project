package com.github.habiteria.integration.service;

import com.github.habiteria.domain.model.Result;
import com.github.habiteria.domain.service.result.ResultService;
import com.github.habiteria.integration.assembler.ResultResourceAssembler;
import com.github.habiteria.integration.resources.ResultResource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Service
public class ResultResourceServiceImpl implements ResultResourceService {
    private final ResultService service;
    private final ResultResourceAssembler resultAsm;

    public ResultResourceServiceImpl(ResultService service, ResultResourceAssembler resultAsm) {
        this.service = service;
        this.resultAsm = resultAsm;
    }

    @Override
    public Resources<ResultResource> getResults(UUID userId, UUID habitId) {
        return assembly(service.getResults(userId, habitId));
    }

    @Override
    public Resources<ResultResource> getResults(UUID userId, LocalDate date) {
        return assembly(service.getResults(userId, date));
    }

    private Resources<ResultResource> assembly(Set<Result> results) {
        return new Resources<>(results.stream()
                .map(resultAsm::toResource)
                .collect(Collectors.toSet()));
    }
}
