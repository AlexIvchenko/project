package com.github.habiteria.integration.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public class ResultsResource extends ResourceSupport {
    private final Set<ResultResource> results;

    public ResultsResource(Set<ResultResource> results) {
        this.results = results;
    }

    @JsonProperty("_embedded")
    public Set<ResultResource> getResults() {
        return results;
    }
}
