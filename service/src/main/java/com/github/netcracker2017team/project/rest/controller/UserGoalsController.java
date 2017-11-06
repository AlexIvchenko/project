package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.service.UserGoalsService;
import com.github.netcracker2017team.project.domain.service.UserTemplatesService;
import com.github.netcracker2017team.project.rest.Rest;
import com.github.netcracker2017team.project.rest.assembler.ContinuationTemplateResourceAssembler;
import com.github.netcracker2017team.project.rest.assembler.PersonalGoalResourceAssembler;
import com.github.netcracker2017team.project.rest.assembler.PersonalStepResourceAssembler;
import com.github.netcracker2017team.project.rest.resources.ContinuationTemplateResource;
import com.github.netcracker2017team.project.rest.resources.PersonalGoalResource;
import com.github.netcracker2017team.project.rest.resources.PersonalStepResource;
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
    private final PersonalStepResourceAssembler stepAsm;

    @Autowired
    public UserGoalsController(UserGoalsService goalsService, UserTemplatesService templatesService, ContinuationTemplateResourceAssembler asm, PersonalGoalResourceAssembler goalAsm, PersonalStepResourceAssembler stepAsm) {
        this.goalsService = goalsService;
        this.templatesService = templatesService;
        this.conTmpAsm = asm;
        this.goalAsm = goalAsm;
        this.stepAsm = stepAsm;
    }

    @PostMapping(path = "/users/{userId}/templates/goals/personal/{goalId}/apply")
    public HttpEntity<PersonalGoalResource> applyUserToHisGoal(@PathVariable("userId") final UUID userId,
                                                               @PathVariable("goalId") final UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.applyUserToHisPersonalGoalTemplate(userId, goalId)));
    }

    @GetMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/toAdd")
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

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/continuations/{continuationId}/add")
    public HttpEntity<PersonalGoalResource> addContinuationTemplateToGoal(@PathVariable("userId") final UUID userId,
                                                                  @PathVariable("goalId") final UUID goalId,
                                                                  @PathVariable("continuationId") final UUID contId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.addContinuationToNewPersonalGoal(userId, goalId, contId)));
    }

    @GetMapping(path = "/users/{userId}/goals/new")
    public HttpEntity<Resources<PersonalGoalResource>> getNewGoals(@PathVariable("userId") UUID userId) {
        return new HttpEntity<>(toResources(goalsService.getNewGoals(userId), goalAsm));
    }

    @PostMapping(path = "/users/{userId}/goals/new/{goalId}/accept")
    public HttpEntity<PersonalGoalResource> acceptGoal(@PathVariable("userId") UUID userId,
                                                        @PathVariable("goalId") UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.userAcceptsHisGoal(userId, goalId)));
    }

    @GetMapping(path = "/users/{userId}/goals/accepted")
    public HttpEntity<Resources<PersonalGoalResource>> getAcceptedGoals(@PathVariable("userId") UUID userId) {
        return new HttpEntity<>(toResources(goalsService.getAcceptedGoals(userId), goalAsm));
    }

    @PostMapping(path = "/users/{userId}/goals/accepted/{goalId}/resolve/success")
    public HttpEntity<PersonalGoalResource> resolveSuccess(@PathVariable("userId") UUID userId,
                                                           @PathVariable("goalId") UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.userResolvesHisGoal(userId, goalId, Goal.Result.SUCCESS)));
    }

    @PostMapping(path = "/users/{userId}/goals/accepted/{goalId}/resolve/fail")
    public HttpEntity<PersonalGoalResource> resolveFail(@PathVariable("userId") UUID userId,
                                                        @PathVariable("goalId") UUID goalId) {
        return new HttpEntity<>(goalAsm.toResource(goalsService.userResolvesHisGoal(userId, goalId, Goal.Result.FAIL)));
    }

    @GetMapping(path = "/users/{userId}/goals/resolved")
    public HttpEntity<Resources<PersonalGoalResource>> getResolvedGoals(@PathVariable("userId") UUID userId) {
        return new HttpEntity<>(toResources(goalsService.getResolvedGoals(userId), goalAsm));
    }

    @GetMapping(path = "/users/{userId}/goals/{goalId}/steps")
    public HttpEntity<Resources<PersonalStepResource>> getSteps(@PathVariable("userId") final UUID userId, @PathVariable("goalId") final UUID goalId) {
        return new HttpEntity<>(toResources(goalsService.getSteps(userId, goalId), stepAsm));
    }

//    public HttpEntity<Resources<PersonalContinuation>> getContinuations(final UUID doerId, final UUID goalId) {
//        return new HttpEntity<>(toResources(goalsService.getContinuations(doerId, goalId)), conTmpAsm);
//    }
}
