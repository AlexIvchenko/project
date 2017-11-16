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

    @Override
    public Set<CalendarRecord> getRecords(Habit habit, LocalDate from, LocalDate to) {
        generate(habit, to.atStartOfDay());
        return recordRepository.findBetween(habit, from.atStartOfDay(), to.plusDays(1).atStartOfDay());
    }


    // TODO cache
    private void generateAll(User user) {
        Set<Habit> habits = habitRepository.findByOwner(user);
        for (Habit habit: habits) {
            generate(habit);
        }
    }

    private void generate(Habit habit) {
        generate(habit, LocalDate.now().plusDays(1).atStartOfDay());
    }

    private void generate(Habit habit, LocalDateTime to) {
        CalendarRecord last = recordRepository.getLastRecord(habit);
        if (last != null) {
            doGenerate(habit, last.getRepeat() + 1, to);
        } else {
            doGenerate(habit, 1, to);
        }
    }

    private void doGenerate(Habit habit, int nextRepeat, LocalDateTime to) {
        Set<CalendarRecord> records = new HashSet<>();

        LocalDate scheduleStart = habit.getSchedule().getStart().toLocalDate();
        LocalDate from = scheduleStart.plusDays(nextRepeat - 1);

        int repeat = nextRepeat;
        for (LocalDate date : new LocalDateRange(from, to.toLocalDate())) {
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
