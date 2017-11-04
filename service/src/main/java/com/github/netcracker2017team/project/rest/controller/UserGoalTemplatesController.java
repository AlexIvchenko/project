package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.assembler.UserGoalTemplateResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.UserGoalTemplateResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import static com.github.netcracker2017team.project.rest.utils.ResourceUtils.toResources;

/**
 * @author Alex Ivchenko
 */
@Rest
@Slf4j
public class UserGoalTemplatesController {
    private final UserTemplatesService service;
    private final UserGoalTemplateResourceAssembler asm;

    @Autowired
    public UserGoalTemplatesController(UserTemplatesService service, UserGoalTemplateResourceAssembler asm) {
        this.service = service;
        this.asm = asm;
    }

    @PostMapping(path = "/users/{id}/templates/goals", produces = "application/hal+json")
    public HttpEntity<UserGoalTemplateResource> createGoalTemplate(@PathVariable("id") final UUID id,
                                                       @RequestBody final UserGoalTemplate template) {
        return new HttpEntity<>(asm.toResource(service.createGoalTemplate(id, template)));
    }

    @GetMapping(path = "/users/{id}/templates/goals", produces = "application/hal+json")
    public HttpEntity<Resources<UserGoalTemplateResource>> getGoalTemplates(@PathVariable("id") final UUID id) {
        return new HttpEntity<>(toResources(service.getGoalTemplates(id), asm));
    }

    @GetMapping(path = "/users/{userId}/templates/goals/{goalId}", produces = MediaTypes.HAL_JSON_VALUE)
    public HttpEntity<UserGoalTemplateResource> getGoalTemplate(@PathVariable("userId") final UUID userId,
                                                                @PathVariable("goalId") final UUID goalId) {
        return new HttpEntity<>(asm.toResource(service.getGoalTemplate(userId, goalId)));
    }
}
