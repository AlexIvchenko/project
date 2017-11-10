package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.model.User;
import com.github.habiteria.core.domain.habit.verifying.detector.UnverifiedHabitsDetector;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.security.UserAuthService;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class RootResourceServiceImpl implements RootResourceService {
    private final UserAuthService authService;
    private final UnverifiedHabitsDetector unverifiedHabitsDetector;

    public RootResourceServiceImpl(UserAuthService authService, UnverifiedHabitsDetector unverifiedHabitsDetector) {
        this.authService = authService;
        this.unverifiedHabitsDetector = unverifiedHabitsDetector;
    }

    @Override
    public ResourceSupport availableActions() {
        ResourceSupport links = new ResourceSupport();
        User user = authService.currentUser();
        if (user == null) {
            fillLinksForAnonymousUser(links);
        } else {
            fillLinksForAuthenticatedUser(user, links);
        }
        return links;
    }

    private void fillLinksForAuthenticatedUser(User user, ResourceSupport links) {
        UUID userId = UUID.fromString(user.getId());
        links.add(Links.getHabits(userId));
        if (unverifiedHabitsDetector.thereAreUncheckedHabits(userId)) {
            links.add(Links.getUncheckedHabits(userId));
        }
    }

    private void fillLinksForAnonymousUser(ResourceSupport links) {
        links.add(Links.signUp());
    }
}
