package com.github.habiteria.core.domain.service.fetcher;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.exceptions.client.ResourceNotFoundException;

/**
 * @author Alex Ivchenko
 */
public interface StrictFetcher {
    User fetchUser(Long id) throws ResourceNotFoundException;

    Habit fetchHabit(Long id) throws ResourceNotFoundException;
}
