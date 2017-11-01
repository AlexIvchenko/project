package com.github.netcracker2017team.project.rest.controller;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.UserGoalTemplate;
import com.github.netcracker2017team.project.domain.repository.UserGoalTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Rest
@Slf4j
public class TemplatesController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGoalTemplateRepository userGoalTemplateRepository;

    @PostMapping(path = "/users/{id}/templates/goals")
    @ResponseBody
    public PersistentEntityResource createGoalTemplate(@PathVariable("id") final UUID id,
                                           @RequestBody final UserGoalTemplate template,
                                           PersistentEntityResourceAssembler asm) {
        User user = userRepository.findOne(id.toString());
        template.setOwner(user);
        template.getSteps().forEach(step -> step.setGoal(template));
        userGoalTemplateRepository.save(template);
        PersistentEntityResource resource = asm.toFullResource(template);
        return resource;
    }

    @GetMapping(path = "/users/{id}/templates/goals")
    @ResponseBody
    public Resources<PersistentEntityResource> getGoalTemplates(@PathVariable("id") final UUID id,
                                                                PersistentEntityResourceAssembler asm) {
        User user = userRepository.findOne(id.toString());
        log.info(String.format("user %s retrieving his personal goal templates: ", user));
        Set<UserGoalTemplate> templates = userGoalTemplateRepository.findByOwner(user);
        log.info("result: " + templates.size());
        for (UserGoalTemplate goal : templates) {
           goal.getSteps().forEach(step -> log.info(step.toString()));
        }
        Resources<PersistentEntityResource> resources = new Resources<>(templates.stream().map(asm::toFullResource).collect(Collectors.toSet()));
        return resources;
    }
}
