package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.personal.PersonalGoal;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UserGoalsService {
    PersonalGoal applyUserToHisPersonalGoalTemplate(UUID userId, UUID goalTemplateId);

    PersonalGoal addContinuationToNewPersonalGoal(UUID userId, UUID goalId, UUID contId);

    PersonalGoal userPublishesHisGoal(UUID userId, UUID goalId);

    Set<PersonalGoal> getPublishedGoals(UUID userId);

    PersonalGoal userResolvesHisGoal(UUID userId, UUID goalId, Goal.Result result);

    Set<PersonalGoal> getNewGoals(UUID userId);
}
