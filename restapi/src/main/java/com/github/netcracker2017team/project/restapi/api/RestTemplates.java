package com.github.netcracker2017team.project.restapi.api;

import org.springframework.web.client.RestTemplate;

/**
 * @author Alex Ivchenko
 */
public interface RestTemplates {
    RestTemplate noAuth();

    RestTemplate basicAuth();

    RestTemplate basicAuth(String username, String password);
}
