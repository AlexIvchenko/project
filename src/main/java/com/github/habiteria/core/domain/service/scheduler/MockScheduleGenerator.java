package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.User;
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
public class MockScheduleGenerator implements ScheduleGenerator {
    private final HabitRepository habitRepository;
    private final CalendarRecordRepository recordRepository;

    public MockScheduleGenerator(HabitRepository habitRepository, CalendarRecordRepository recordRepository) {
        this.habitRepository = habitRepository;
        this.recordRepository = recordRepository;
    }

    // TODO cache
    @Override
    public void generateAll(User user) {
        Set<Habit> habits = habitRepository.findByOwner(user);
        for (Habit habit: habits) {
            generate(habit);
        }
    }

    @Override
    public void generate(Habit habit) {
        generate(habit, LocalDate.now());
    }


    private void generate(Habit habit, LocalDate to) {
        CalendarRecord last = recordRepository.getLastRecord(habit);
        if (last != null) {
            doGenerate(habit, last.getRepeat() + 1, to);
        } else {
            doGenerate(habit, 1, to);
        }
    }

    private void doGenerate(Habit habit, int nextRepeat, LocalDate to) {
        Set<CalendarRecord> records = new HashSet<>();

        LocalDate scheduleStart = habit.getSchedule().getStart().toLocalDate();
        LocalDate from = scheduleStart.plusDays(nextRepeat - 1);

        int repeat = nextRepeat;
        for (LocalDate date : new LocalDateRange(from, to)) {
            CalendarRecord record = new CalendarRecord();
            record.setStatus(Status.UNVERIFIED);
            record.setHabit(habit);
            record.setRepeat(repeat++);

            LocalDateTime startDoing = date.atStartOfDay();
            LocalDateTime startVerifying = date.atStartOfDay();
            LocalDateTime endDoing = date.plusDays(1).atStartOfDay();
            LocalDateTime endVerifying = date.plusDays(1).atStartOfDay();

            record.setStartDoing(startDoing);
            record.setEndDoing(endDoing);

            record.setStartVerifying(startVerifying);
            record.setEndVerifying(endVerifying);

            records.add(record);
        }

        recordRepository.save(records);
    }
}
