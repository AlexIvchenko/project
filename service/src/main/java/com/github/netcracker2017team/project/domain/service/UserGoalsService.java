package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.Goal;
import com.github.netcracker2017team.project.domain.model.personal.PersonalGoal;
import com.github.netcracker2017team.project.domain.model.personal.PersonalStep;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
public interface UserGoalsService {
    PersonalGoal applyUserToHisPersonalGoalTemplate(UUID userId, UUID goalTemplateId);

    PersonalGoal addContinuationToNewPersonalGoal(UUID userId, UUID goalId, UUID contId);

    PersonalGoal userAcceptsHisGoal(UUID userId, UUID goalId);

    PersonalGoal userResolvesHisGoal(UUID userId, UUID goalId, Goal.Result result);

    Set<PersonalGoal> getNewGoals(UUID userId);

    Set<PersonalGoal> getAcceptedGoals(UUID userId);

    Set<PersonalGoal> getResolvedGoals(UUID userId);

    Set<PersonalStep> getSteps(UUID doerId, UUID goalId);
}
