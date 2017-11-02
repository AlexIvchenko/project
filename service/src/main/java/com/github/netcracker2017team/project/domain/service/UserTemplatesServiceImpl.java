package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.PersonalGoal;
import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.UserGoalTemplate;
import com.github.netcracker2017team.project.domain.repository.PersonalGoalRepository;
import com.github.netcracker2017team.project.domain.repository.UserContinuationTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserGoalTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Component
public class UserTemplatesServiceImpl implements UserTemplatesService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGoalTemplateRepository userGoalTemplateRepository;
    @Autowired
    private UserContinuationTemplateRepository userContinuationTemplateRepository;
    @Autowired
    private PersonalGoalRepository personalGoalRepository;

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
    public Goal applyUserToHisPersonalGoal(UUID userId, UUID goalId) {
        User user = userRepository.findOne(userId.toString());
        UserGoalTemplate template = userGoalTemplateRepository.findOne(goalId.toString());
        PersonalGoal goal = new PersonalGoal(user, template);
        return personalGoalRepository.save(goal);
    }

    @Override
    public Goal addContinuationToNewPersonalGoal(UUID userId, UUID goalId, UUID contId) {
        User user = userRepository.findOne(userId.toString());
        PersonalGoal goal = personalGoalRepository.findOne(goalId.toString());
        UserContinuationTemplate template = userContinuationTemplateRepository.findOne(contId.toString());
        goal.addContinuation(template);
        return personalGoalRepository.save(goal);
    }

    @Override
    public Goal userPublishesHisGoal(UUID userId, UUID goalId) {
        User user = userRepository.findOne(userId.toString());
        PersonalGoal goal = personalGoalRepository.findOne(goalId.toString());
        goal.publish();
        return personalGoalRepository.save(goal);
    }

    @Override
    public Set<PersonalGoal> getPublishedGoals(UUID userId) {
        User user = userRepository.findOne(userId.toString());
        return personalGoalRepository.findPublished(user);
    }

    @Override
    public Set<PersonalGoal> getNewGoals(UUID userId) {
        User user = userRepository.findOne(userId.toString());
        return personalGoalRepository.findNew(user);
    }
}
