package com.github.habiteria.integration.service;

import com.github.habiteria.domain.service.ResultService;
import com.github.habiteria.integration.assembler.ResultsResourceAssembler;
import com.github.habiteria.integration.resources.ResultsResource;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class ResultResourceServiceImpl implements ResultResourceService {
    private final ResultService service;
    private final ResultsResourceAssembler resultsAsm;

    public ResultResourceServiceImpl(ResultService service, ResultsResourceAssembler resultsAsm) {
        this.service = service;
        this.resultsAsm = resultsAsm;
    }

    @Override
    public ResultsResource getResults(UUID userId, UUID habitId) {
        return resultsAsm.toResource(service.getResults(userId, habitId));
    }
}
