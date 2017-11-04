package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.service.UserGoalsService;
import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.assembler.ContinuationTemplateResourceAssembler;
import com.github.netcracker2017team.project.rest.assembler.PersonalGoalResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.ContinuationTemplateResource;
import com.github.netcracker2017team.project.rest.resources.PersonalGoalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private final UserGoalsService goalsService;
    private final UserTemplatesService templatesService;
    private final ContinuationTemplateResourceAssembler conTmpAsm;
    private final PersonalGoalResourceAssembler goalAsm;

    @Autowired
    public UserGoalsController(UserGoalsService goalsService, UserTemplatesService templatesService, ContinuationTemplateResourceAssembler asm, PersonalGoalResourceAssembler goalAsm) {
        this.goalsService = goalsService;
        this.templatesService = templatesService;
        this.conTmpAsm = asm;
        this.goalAsm = goalAsm;
    }

    @GetMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/toAdd", produces = "application/hal+json")
    public HttpEntity<Resources<ContinuationTemplateResource>> getContinuationTemplatesToAdd(@PathVariable("userId") final UUID userId,
                                                                                        @PathVariable("goalId") final UUID goalId) {
        Set<ContinuationTemplateResource> resources = templatesService.getContinuationTemplates(userId)
                .stream().map(template -> {
                    ContinuationTemplateResource resource = conTmpAsm.toResource(template);
                    UUID contId = UUID.fromString(template.getId());
                    resource.add(linkTo(methodOn(UserGoalsController.class).addContinuationTemplateToGoal(userId, goalId, contId)).withRel("add"));
                    return resource;
                }).collect(Collectors.toSet());
        return new HttpEntity<>(new Resources<>(resources));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/{continuationId}/add", produces = "application/hal+json")
    public HttpEntity<PersonalGoalResource> addContinuationTemplateToGoal(@PathVariable("userId") final UUID userId,
                                                                  @PathVariable("goalId") final UUID goalId,
                                                                  @PathVariable("continuationId") final UUID contId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.addContinuationToNewPersonalGoal(userId, goalId, contId)));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/publish", produces = "application/hal+json")
    public HttpEntity<PersonalGoalResource> publishGoal(@PathVariable("userId") UUID userId,
                                                @PathVariable("goalId") UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.userPublishesHisGoal(userId, goalId)));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/resolve/success", produces = "application/hal+json")
    public HttpEntity<PersonalGoalResource> resolveSuccess(@PathVariable("userId") UUID userId,
                                                @PathVariable("goalId") UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.userResolvesHisGoal(userId, goalId, Goal.Result.SUCCESS)));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/resolve/fail", produces = "application/hal+json")
    public HttpEntity<PersonalGoalResource> resolveFail(@PathVariable("userId") UUID userId,
                                                   @PathVariable("goalId") UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.userResolvesHisGoal(userId, goalId, Goal.Result.FAIL)));
    }

    @PostMapping(path = "/users/{userId}/templates/goals/personal/{goalId}/apply", produces = "application/hal+json")
    public HttpEntity<PersonalGoalResource> applyUserToHisGoal(@PathVariable("userId") final UUID userId,
                                                       @PathVariable("goalId") final UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.applyUserToHisPersonalGoalTemplate(userId, goalId)));
    }

    @GetMapping(path = "/users/{userId}/goals/new", produces = "application/hal+json")
    public HttpEntity<Resources<PersonalGoalResource>> getNewGoals(@PathVariable("userId") UUID userId) {
        return new HttpEntity<>(toResources(goalsService.getNewGoals(userId), goalAsm));
    }

    @GetMapping(path = "/users/{userId}/goals/published", produces = "application/hal+json")
    public HttpEntity<Resources<PersonalGoalResource>> getPublishedGoals(@PathVariable("userId") UUID userId) {
        return new HttpEntity<>(toResources(goalsService.getPublishedGoals(userId), goalAsm));
    }
}
