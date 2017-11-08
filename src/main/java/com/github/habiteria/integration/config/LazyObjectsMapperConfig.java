package com.github.habiteria.integration.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Configuration
public class LazyObjectsMapperConfig {
    /**
     * This module enables correct hibernate proxy classes mapping
     */
    @Bean
    public Module datatypeHibernateModule() {
        return new Hibernate5Module();
    }
}
