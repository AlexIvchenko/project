package com.github.habiteria.core.domain.service.karma;

import com.github.habiteria.core.entities.Karma;
import com.github.habiteria.core.entities.User;

/**
 * @author Alex Ivchenko
 */
public interface KarmaService {
    Karma create(User user);

    Karma current(Long userId);
}
