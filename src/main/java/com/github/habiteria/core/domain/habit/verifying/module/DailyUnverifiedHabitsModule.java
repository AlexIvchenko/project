package com.github.habiteria.core.domain.habit.verifying.module;

import com.github.habiteria.core.model.ScheduleType;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Result;
import com.github.habiteria.core.model.User;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.core.repository.ResultRepository;
import com.github.habiteria.core.repository.UserRepository;
import com.github.habiteria.core.domain.habit.tracking.HabitSnapshot;
import com.github.habiteria.core.domain.habit.tracking.module.DailyTrackingModule;
import com.github.habiteria.utils.LocalDateRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Component
public class DailyUnverifiedHabitsModule implements UnverifiedHabitsModule {
    private final DailyTrackingModule module;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final ResultRepository resultRepository;

    public DailyUnverifiedHabitsModule(DailyTrackingModule module, UserRepository userRepository, HabitRepository habitRepository, ResultRepository resultRepository) {
        this.module = module;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public ScheduleType supports() {
        return ScheduleType.DAILY;
    }

    @Override
    public boolean thereAreUncheckedHabits(UUID userId) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return module.getHabits(userId, yesterday)
                .stream()
                .anyMatch(HabitSnapshot::isUnverified);
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        log.info("fail verifying by {}", userId);
        User user = userRepository.findOne(userId.toString());
        Set<Habit> habits = habitRepository.findByOwnerAndScheduleType(user, ScheduleType.DAILY);
        Set<Result> fails = new HashSet<>();
        for (Habit habit : habits) {
            Set<Result> results = resultRepository.findByHabit(habit);
            LocalDate yesterday = LocalDate.now().minusDays(1);
            for (LocalDate date : new LocalDateRange(habit.getStart(), yesterday)) {
                boolean resultIsFound = false;
                for (Result result : results) {
                    if (result.getDate().equals(date)) {
                        resultIsFound = true;
                        break;
                    }
                }
                if (!resultIsFound) {
                    Result fail = Result.fail(habit, date);
                    fails.add(fail);
                }
            }
        }
        resultRepository.save(fails);
    }
}
