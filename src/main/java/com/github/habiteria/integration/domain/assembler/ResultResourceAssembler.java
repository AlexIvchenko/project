package com.github.habiteria.integration.domain.assembler;

import com.github.habiteria.core.model.Result;
import com.github.habiteria.integration.domain.resources.ResultResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class ResultResourceAssembler implements ResourceAssembler<Result, ResultResource> {
    @Override
    public ResultResource toResource(Result entity) {
        return new ResultResource(entity.getDate(), entity.getStatus());
    }
}
