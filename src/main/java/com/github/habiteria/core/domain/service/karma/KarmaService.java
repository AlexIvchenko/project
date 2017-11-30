package com.github.habiteria.core.domain.service.karma;

import com.github.habiteria.core.entities.Karma;

/**
 * @author Alex Ivchenko
 */
public interface KarmaService {
    Karma current(Long userId);
}
