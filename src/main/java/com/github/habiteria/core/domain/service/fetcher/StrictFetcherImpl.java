package com.github.habiteria.core.domain.service.fetcher;

import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.exceptions.client.ResourceNotFoundException;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * @author Alex Ivchenko
 */
@Service
public class StrictFetcherImpl implements StrictFetcher {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public StrictFetcherImpl(UserRepository userRepository, HabitRepository habitRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public User fetchUser(Long id) throws ResourceNotFoundException {
        return fetchOrThrowIfNull(() -> userRepository.findOne(id),
                new ResourceNotFoundException(User.class.getName(), id));
    }

    @Override
    public Habit fetchHabit(Long id) throws ResourceNotFoundException {
        return fetchOrThrowIfNull(() -> habitRepository.findOne(id),
                new ResourceNotFoundException(Habit.class.getName(), id));
    }

    private static <T> T fetchOrThrowIfNull(Supplier<T> fetcher, ResourceNotFoundException exc) {
        T fetched = fetcher.get();
        if (fetched == null) {
            throw exc;
        }
        return fetched;
    }
}
