package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.assembler.UserStepTemplateResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.UserStepTemplateResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

import static com.github.netcracker2017team.project.rest.utils.ResourceUtils.toResources;

/**
 * @author Alex Ivchenko
 */
@Rest
@Slf4j
public class UserStepTemplatesController {
    @Autowired
    private UserTemplatesService service;

    @Autowired
    private UserStepTemplateResourceAssembler asm;

    @GetMapping(path = "/users/{userId}/templates/goals/{goalId}/steps")
    public HttpEntity<Resources<UserStepTemplateResource>> getStepTemplates(@PathVariable("userId") final UUID userId,
                                                                           @PathVariable("goalId") final UUID goalId) {
        Resources<UserStepTemplateResource> resources = toResources(service.getStepTemplates(userId, goalId), asm);
        return new HttpEntity<>(resources);
    }
}
