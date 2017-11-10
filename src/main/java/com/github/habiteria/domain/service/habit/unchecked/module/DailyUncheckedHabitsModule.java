package com.github.habiteria.domain.service.habit.unchecked.module;

import com.github.habiteria.domain.model.ScheduleType;
import com.github.habiteria.domain.model.Habit;
import com.github.habiteria.domain.model.Result;
import com.github.habiteria.domain.model.User;
import com.github.habiteria.domain.repository.HabitRepository;
import com.github.habiteria.domain.repository.ResultRepository;
import com.github.habiteria.domain.repository.UserRepository;
import com.github.habiteria.domain.service.habit.core.HabitSnapshot;
import com.github.habiteria.domain.service.habit.core.module.DailyScheduleModule;
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
public class DailyUncheckedHabitsModule implements UncheckedHabitsModule {
    private final DailyScheduleModule module;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final ResultRepository resultRepository;

    public DailyUncheckedHabitsModule(DailyScheduleModule module, UserRepository userRepository, HabitRepository habitRepository, ResultRepository resultRepository) {
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
                .anyMatch(HabitSnapshot::isUnchecked);
    }

    @Override
    public void failUncheckedHabits(UUID userId) {
        log.info("fail unchecked by {}", userId);
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
