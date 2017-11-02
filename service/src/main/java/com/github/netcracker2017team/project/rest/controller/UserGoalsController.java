package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.service.UserGoalsService;
import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.netcracker2017team.project.rest.utils.ResourceUtils.toResources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Rest
public class UserGoalsController {
    @Autowired
    private UserGoalsService goalsService;
    @Autowired
    private UserTemplatesService templatesService;

    @GetMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/toAdd")
    @ResponseBody
    public Resources<PersistentEntityResource> getContinuationTemplatesToAdd(@PathVariable("userId") final UUID userId,
                                                                             @PathVariable("goalId") final UUID goalId,
                                                                             PersistentEntityResourceAssembler asm) {
        Set<PersistentEntityResource> resources = templatesService.getContinuationTemplates(userId)
                .stream().map(template -> {
                    PersistentEntityResource resource = asm.toFullResource(template);
                    UUID contId = UUID.fromString(template.getId());
                    resource.add(linkTo(methodOn(UserGoalsController.class).addContinuationTemplateToGoal(userId, goalId, contId, null)).withRel("add"));
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
        return asm.toFullResource(goalsService.addContinuationToNewPersonalGoal(userId, goalId, contId));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/publish")
    @ResponseBody
    public PersistentEntityResource publishGoal(@PathVariable("userId") UUID userId,
                                                @PathVariable("goalId") UUID goalId,
                                                PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(goalsService.userPublishesHisGoal(userId, goalId));
    }

    @PostMapping(path = "/users/{userId}/templates/goals/personal/{goalId}/apply")
    @ResponseBody
    public PersistentEntityResource applyUserToHisGoal(@PathVariable("userId") final UUID userId,
                                                       @PathVariable("goalId") final UUID goalId,
                                                       PersistentEntityResourceAssembler asm) {
        return asm.toFullResource(goalsService.applyUserToHisPersonalGoalTemplate(userId, goalId));
    }

    @GetMapping(path = "/users/{userId}/goals/new")
    @ResponseBody
    public Resources<PersistentEntityResource> getNewGoals(@PathVariable("userId") UUID userId,
                                                           PersistentEntityResourceAssembler asm) {
        return toResources(goalsService.getNewGoals(userId), asm);
    }

    @GetMapping(path = "/users/{userId}/goals/published")
    @ResponseBody
    public Resources<PersistentEntityResource> getPublishedGoals(@PathVariable("userId") UUID userId,
                                                                 PersistentEntityResourceAssembler asm) {
        return toResources(goalsService.getPublishedGoals(userId), asm);
    }
}
