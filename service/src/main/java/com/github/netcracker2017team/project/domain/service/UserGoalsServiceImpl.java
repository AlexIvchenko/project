package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.personal.PersonalGoal;
import com.github.netcracker2017team.project.domain.model.User;
import com.github.netcracker2017team.project.domain.model.template.user.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
import com.github.netcracker2017team.project.domain.repository.PersonalGoalRepository;
import com.github.netcracker2017team.project.domain.repository.UserContinuationTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserGoalTemplateRepository;
import com.github.netcracker2017team.project.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class UserGoalsServiceImpl implements UserGoalsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonalGoalRepository personalGoalRepository;
    @Autowired
    private UserGoalTemplateRepository userGoalTemplateRepository;
    @Autowired
    private UserContinuationTemplateRepository userContinuationTemplateRepository;

    @Override
    public Goal applyUserToHisPersonalGoalTemplate(UUID userId, UUID goalTemplateId) {
        User user = userRepository.findOne(userId.toString());
        UserGoalTemplate template = userGoalTemplateRepository.findOne(goalTemplateId.toString());
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
