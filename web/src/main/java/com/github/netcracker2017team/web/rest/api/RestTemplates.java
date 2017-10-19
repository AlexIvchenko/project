package com.github.netcracker2017team.web.rest.api;

import org.springframework.web.client.RestTemplate;

/**
 * @author Alex Ivchenko
 */
public interface RestTemplates {
    RestTemplate noAuth();

    RestTemplate basicAuth(String username, String password);
}
