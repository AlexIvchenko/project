package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.template.user.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
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
    private final UserTemplatesService service;

    @Autowired
    public UserTemplatesController(UserTemplatesService service) {
        this.service = service;
    }

    @PostMapping(path = "/users/{id}/templates/goals", produces = "application/hal+json")
    @ResponseBody
    public PersistentEntityResource createGoalTemplate(@PathVariable("id") final UUID id,
                                                       @RequestBody final UserGoalTemplate template,
                                                       PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.createGoalTemplate(id, template));
    }

    @GetMapping(path = "/users/{id}/templates/goals", produces = "application/hal+json")
    @ResponseBody
    public Resources<PersistentEntityResource> getGoalTemplates(@PathVariable("id") final UUID id,
                                                                PersistentEntityResourceAssembler asm) {
        return toResources(service.getGoalTemplates(id), asm);
    }

    @PostMapping(path = "/users/{id}/templates/continuations", produces = "application/hal+json")
    @ResponseBody
    public PersistentEntityResource createContinuationTemplate(@PathVariable("id") final UUID id,
                                                               @RequestBody final UserContinuationTemplate template,
                                                               PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.createContinuationTemplate(id, template));
    }

    @GetMapping(path = "/users/{id}/templates/continuations", produces = "application/hal+json")
    @ResponseBody
    public Resources<PersistentEntityResource> getContinuationTemplates(@PathVariable("id") final UUID id,
                                                                        PersistentEntityResourceAssembler asm) {
        return toResources(service.getContinuationTemplates(id), asm);
    }
}
