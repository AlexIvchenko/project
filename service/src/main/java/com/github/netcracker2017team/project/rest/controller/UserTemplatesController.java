package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.template.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.UserGoalTemplate;
import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.github.netcracker2017team.project.rest.utils.ResourceUtils.toResources;

/**
 * @author Alex Ivchenko
 */
@Rest
@Slf4j
public class UserTemplatesController {

    @Autowired
    private UserTemplatesService service;

    @PostMapping(path = "/users/{id}/templates/goals")
    @ResponseBody
    public PersistentEntityResource createGoalTemplate(@PathVariable("id") final UUID id,
                                                       @RequestBody final UserGoalTemplate template,
                                                       PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.createGoalTemplate(id, template));
    }

    @GetMapping(path = "/users/{id}/templates/goals")
    @ResponseBody
    public Resources<PersistentEntityResource> getGoalTemplates(@PathVariable("id") final UUID id,
                                                                PersistentEntityResourceAssembler asm) {
        return toResources(service.getGoalTemplates(id), asm);
    }

    @PostMapping(path = "/users/{id}/templates/continuations")
    @ResponseBody
    public PersistentEntityResource createContinuationTemplate(@PathVariable("id") final UUID id,
                                                               @RequestBody final UserContinuationTemplate template,
                                                               PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.createContinuationTemplate(id, template));
    }

    @GetMapping(path = "/users/{id}/templates/continuations")
    @ResponseBody
    public Resources<PersistentEntityResource> getContinuationTemplates(@PathVariable("id") final UUID id,
                                                                        PersistentEntityResourceAssembler asm) {
        return toResources(service.getContinuationTemplates(id), asm);
    }
}