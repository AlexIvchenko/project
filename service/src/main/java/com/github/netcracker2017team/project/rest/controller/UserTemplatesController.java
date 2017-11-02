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

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

    @PostMapping(path = "/users/{userId}/templates/goals/personal/{goalId}/apply")
    @ResponseBody
    public PersistentEntityResource applyUserToHisGoal(@PathVariable("userId") final UUID userId,
                                                       @PathVariable("goalId") final UUID goalId,
                                                       PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.applyUserToHisPersonalGoal(userId, goalId));
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

    @GetMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/toAdd")
    @ResponseBody
    public Resources<PersistentEntityResource> getContinuationTemplatesToAdd(@PathVariable("userId") final UUID userId,
                                                                             @PathVariable("goalId") final UUID goalId,
                                                                             PersistentEntityResourceAssembler asm) {
        Set<PersistentEntityResource> resources = service.getContinuationTemplates(userId)
                .stream().map(template -> {
                    PersistentEntityResource resource = asm.toFullResource(template);
                    UUID contId = UUID.fromString(template.getId());
                    resource.add(linkTo(methodOn(UserTemplatesController.class).addContinuationTemplateToGoal(userId, goalId, contId, null)).withRel("add"));
                    return resource;
                }).collect(Collectors.toSet());
        return new Resources<>(resources);
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/{continuationId}/add")
    @ResponseBody
    public PersistentEntityResource addContinuationTemplateToGoal(@PathVariable("userId") final UUID userId,
                                                                             @PathVariable("goalId") final UUID goalId,
                                                                             @PathVariable("continuationId") final UUID contId,
                                                                             PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.addContinuationToNewPersonalGoal(userId, goalId, contId));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/publish")
    @ResponseBody
    public PersistentEntityResource publishGoal(@PathVariable("userId") UUID userId,
                                                @PathVariable("goalId") UUID goalId,
                                                PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(service.userPublishesHisGoal(userId, goalId));
    }

    @GetMapping(path = "/users/{userId}/goals/new")
    @ResponseBody
    public Resources<PersistentEntityResource> getNewGoals(@PathVariable("userId") UUID userId,
                                                                 PersistentEntityResourceAssembler asm) {
        return toResources(service.getNewGoals(userId), asm);
    }

    @GetMapping(path = "/users/{userId}/goals/published")
    @ResponseBody
    public Resources<PersistentEntityResource> getPublishedGoals(@PathVariable("userId") UUID userId,
                                                PersistentEntityResourceAssembler asm) {
        return toResources(service.getPublishedGoals(userId), asm);
    }

    private <T> Resources<PersistentEntityResource> toResources(Collection<T> collection, PersistentEntityResourceAssembler asm) {
        return new Resources<>(collection.stream().map(asm::toFullResource).collect(Collectors.toSet()));
    }
}
