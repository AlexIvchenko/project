package com.github.habiteria.core.domain.service.user;

import com.github.habiteria.core.domain.service.karma.KarmaService;
import com.github.habiteria.core.entities.User;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class UserServiceImpl implements UserService {
    private final KarmaService karmaService;

    public UserServiceImpl(KarmaService karmaService) {
        this.karmaService = karmaService;
    }

    @Override
    public User setUpUserEnv(User user) {
        karmaService.create(user);
        return user;
    }
}
