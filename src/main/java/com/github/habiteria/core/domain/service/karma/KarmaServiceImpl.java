package com.github.habiteria.core.domain.service.karma;

import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.*;
import com.github.habiteria.core.entities.imps.KarmaImpl;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.KarmaRepository;
import com.github.habiteria.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class KarmaServiceImpl implements KarmaService {
    private final KarmaRepository karmaRepository;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final Scheduler scheduler;

    public KarmaServiceImpl(KarmaRepository karmaRepository, UserRepository userRepository, HabitRepository habitRepository, Scheduler scheduler) {
        this.karmaRepository = karmaRepository;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.scheduler = scheduler;
    }

    @Override
    public Karma current(Long userId) {
        User user = userRepository.findOne(userId);
        KarmaImpl karma = karmaRepository.getByOwner(user);
        LocalDateTime old = karma.getActualTime();
        LocalDateTime updated = LocalDateTime.now();
        Set<Habit> habits = habitRepository.findByOwner(user);
        int delta = 0;
        for (Habit habit : habits) {
            Set<CalendarRecord> records = scheduler.getRecords(habit, old.toLocalDate(), updated.toLocalDate());
            for (CalendarRecord record : records) {
                if (record.getEndVerifying().isBefore(old)) {
                    if (record.getStatus() == Status.FAIL) {
                        delta -= 2;
                    } else if (record.getStatus() == Status.SUCCESS) {
                        delta += 1;
                    }
                }
            }
        }
        int newValue = karma.getValue() + delta;
        if (newValue < 0) {
            newValue = 0;
        }
        karma.update(newValue, updated);
        return karmaRepository.save(karma);
    }
}
