package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.user.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
import com.github.netcracker2017team.project.domain.model.template.user.UserStepTemplate;
import com.github.netcracker2017team.project.domain.repository.UserContinuationTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserGoalTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class UserTemplatesServiceImpl implements UserTemplatesService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGoalTemplateRepository userGoalTemplateRepository;
    @Autowired
    private UserContinuationTemplateRepository userContinuationTemplateRepository;


    @Override
    public UserGoalTemplate createGoalTemplate(UUID userId, UserGoalTemplate template) {
        User user = userRepository.findOne(userId.toString());
        template.setOwner(user);
        template.getSteps().forEach(step -> step.setGoal(template));
        userGoalTemplateRepository.save(template);
        return template;
    }

    @Override
    public Set<UserGoalTemplate> getGoalTemplates(UUID userId) {
        User user = userRepository.findOne(userId.toString());
        log.info(String.format("doer: %s retrieving his personal goal templates", user));
        Set<UserGoalTemplate> templates = userGoalTemplateRepository.findByOwner(user);
        log.info("result size: " + templates.size());
        return templates;
    }

    @Override
    public UserContinuationTemplate createContinuationTemplate(UUID userId, UserContinuationTemplate template) {
        User user = userRepository.findOne(userId.toString());
        template.setOwner(user);
        log.info(String.format("doer: %s creating his personal continuation template: %s", user, template));
        userContinuationTemplateRepository.save(template);
        return template;
    }

    @Override
    public Set<UserContinuationTemplate> getContinuationTemplates(UUID userId) {
        User user = userRepository.findOne(userId.toString());
        log.info(String.format("doer: %s retrieving his personal continuation templates", user));
        Set<UserContinuationTemplate> templates = userContinuationTemplateRepository.findByOwner(user);
        log.info("result size: " + templates.size());
        return templates;
    }

    @Override
    public Set<UserStepTemplate> getStepTemplates(UUID userId, UUID goalId) {
        UserGoalTemplate goalTemplate = userGoalTemplateRepository.findOne(goalId.toString());
        HashSet<UserStepTemplate> steps = new HashSet<>(goalTemplate.getSteps());
        log.info("result size: " + steps.size());
        return steps;
    }

    @Override
    public UserGoalTemplate getGoalTemplate(UUID userId, UUID goalId) {
        return userGoalTemplateRepository.findOne(goalId.toString());
    }
}
