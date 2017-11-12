package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Status;
import com.github.habiteria.core.model.User;
import com.github.habiteria.core.repository.CalendarRecordRepository;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.utils.LocalDateRange;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class MockScheduler implements Scheduler {

    private final CalendarRecordRepository recordRepository;
    private final HabitRepository habitRepository;

    public MockScheduler(CalendarRecordRepository recordRepository, HabitRepository habitRepository) {
        this.recordRepository = recordRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public CalendarRecord getRecord(Habit habit, int repeat) {
        generate(habit);
        return recordRepository.findOne(habit, repeat);
    }

    @Override
    public CalendarRecord update(CalendarRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public Set<CalendarRecord> findVerifiable(User user) {
        generateAll(user);
        return recordRepository.findVerifiableIn(user, LocalDateTime.now());
    }


    // TODO cache
    private void generateAll(User user) {
        Set<Habit> habits = habitRepository.findByOwner(user);
        for (Habit habit: habits) {
            generate(habit);
        }
    }

    private void generate(Habit habit) {
        CalendarRecord record = recordRepository.getLastRecord(habit);
        if (record == null) {
            generate(habit, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        } else if (record.getEndDoing().isBefore(LocalDateTime.now())) {
            generate(habit, record.getStartDoing(), LocalDateTime.now().plusDays(1));
        }
    }

    private void generate(Habit habit, LocalDateTime from, LocalDateTime to) {
        Set<CalendarRecord> records = new HashSet<>();
        int repeat = 1;
        for (LocalDate date : new LocalDateRange(from.toLocalDate(), to.toLocalDate())) {
            CalendarRecord record = new CalendarRecord();
            record.setStatus(Status.UNVERIFIED);
            record.setHabit(habit);
            record.setRepeat(repeat++);

            record.setStartDoing(date.atStartOfDay());
            record.setEndDoing(date.plusDays(1).atStartOfDay());

            record.setStartVerifying(date.atStartOfDay());
            record.setEndVerifying(date.plusDays(1).atStartOfDay());

            records.add(record);
        }

        recordRepository.save(records);
    }
}
