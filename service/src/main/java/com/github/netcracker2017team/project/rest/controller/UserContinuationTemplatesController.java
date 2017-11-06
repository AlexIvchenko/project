package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.template.user.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.assembler.ContinuationTemplateResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.ContinuationTemplateResource;
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
public class UserContinuationTemplatesController {
    private final UserTemplatesService service;
    private final ContinuationTemplateResourceAssembler asm;

    public UserContinuationTemplatesController(UserTemplatesService service, ContinuationTemplateResourceAssembler asm) {
        this.service = service;
        this.asm = asm;
    }

    @PostMapping(path = "/users/{id}/templates/continuations")
    public HttpEntity<ContinuationTemplateResource> createContinuationTemplate(@PathVariable("id") final UUID id,
                                                                               @RequestBody final UserContinuationTemplate template) {
        return new HttpEntity<>(asm.toResource(service.createContinuationTemplate(id, template)));
    }

    @GetMapping(path = "/users/{id}/templates/continuations")
    public HttpEntity<Resources<ContinuationTemplateResource>> getContinuationTemplates(@PathVariable("id") final UUID id) {
        return new HttpEntity<>(toResources(service.getContinuationTemplates(id), asm));
    }
}
