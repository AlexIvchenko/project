package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.security.UserAuthService;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class RootResourceServiceImpl implements RootResourceService {
    private final UserAuthService authService;

    public RootResourceServiceImpl(UserAuthService authService) {
        this.authService = authService;
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
        Long userId = user.getId();
        links.add(Links.getHabits(userId));
        links.add(Links.createHabit(userId));
        links.add(Links.getCurrentHabitList(userId));
    }

    private void fillLinksForAnonymousUser(ResourceSupport links) {
        links.add(Links.signUp());
    }
}
