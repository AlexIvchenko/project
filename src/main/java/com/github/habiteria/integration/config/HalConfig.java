package com.github.habiteria.integration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * annotation EnableHypermediaSupport is not compatible with Spring Boot's Jackson2ObjectMapperBuilder
 * (issue #333 https://github.com/spring-projects/spring-hateoas/issues/333)
 * This is the simplest way to fix it
 * @author Alex Ivchenko
 */
// TODO add tests to test that date format is correct
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Configuration
public class HalConfig {
    private final ObjectMapper _halObjectMapper;

    @Autowired
    public HalConfig(ObjectMapper _halObjectMapper) {
        this._halObjectMapper = _halObjectMapper;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilder() {
        return builder -> builder.configure(_halObjectMapper);
    }
}
