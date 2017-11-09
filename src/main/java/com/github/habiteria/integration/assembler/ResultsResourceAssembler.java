package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.Result;
import com.github.habiteria.integration.resources.ResultResource;
import com.github.habiteria.integration.resources.ResultsResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Component
public class ResultsResourceAssembler implements ResourceAssembler<Set<Result>, ResultsResource> {
    private final ResultResourceAssembler resultAsm;

    public ResultsResourceAssembler(ResultResourceAssembler resultAsm) {
        this.resultAsm = resultAsm;
    }

    @Override
    public ResultsResource toResource(Set<Result> entity) {
        Set<ResultResource> resources = entity.stream()
                .map(resultAsm::toResource)
                .collect(Collectors.toSet());
        return new ResultsResource(resources);
    }
}
