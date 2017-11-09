package com.github.habiteria.integration.service;

import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.service.HabitSnapshotService;
import com.github.habiteria.integration.links.Links;
import com.github.habiteria.security.UserAuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Service
public class RootResourceServiceImpl implements RootResourceService {
    private final UserAuthService authService;
    private final HabitSnapshotService habitService;

    public RootResourceServiceImpl(UserAuthService authService,
                                   @Qualifier("habitSnapshotService") HabitSnapshotService habitService) {
        this.authService = authService;
        this.habitService = habitService;
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
        if (habitService.thereAreUncheckedHabits(userId)) {
            links.add(Links.getUncheckedHabits(userId));
        }
    }

    private void fillLinksForAnonymousUser(ResourceSupport links) {
        links.add(Links.signUp());
    }
}
