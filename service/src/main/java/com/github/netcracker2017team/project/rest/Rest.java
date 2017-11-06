package com.github.netcracker2017team.project.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

/**
 * @author Alex Ivchenko
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(path = "/api", produces = HAL_JSON_VALUE)
@RestController
public @interface Rest {
}
