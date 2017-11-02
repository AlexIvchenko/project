package com.github.netcracker2017team.project.domain.service;

import com.github.netcracker2017team.project.domain.model.template.user.UserContinuationTemplate;
import com.github.netcracker2017team.project.domain.model.template.user.UserGoalTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Transactional
public interface UserTemplatesService {
    UserGoalTemplate createGoalTemplate(final UUID userId, final UserGoalTemplate template);

    Set<UserGoalTemplate> getGoalTemplates(final UUID userId);

    UserContinuationTemplate createContinuationTemplate(final UUID userId, final UserContinuationTemplate template);

    Set<UserContinuationTemplate> getContinuationTemplates(final UUID userId);
}
