package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.Result;
import com.github.habiteria.integration.resources.ResultResource;
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
