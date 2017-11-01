package com.github.netcracker2017team.project.rest.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * @author Alex Ivchenko
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RepositoryRestController
@RequestMapping(path = "/api")
public @interface Rest {
}
