package com.github.habiteria.integration.service;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
public interface RootResourceService {
    ResourceSupport availableActions();
}
