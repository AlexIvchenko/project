package com.github.habiteria.core.domain.habit;

import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.Status;
import com.github.habiteria.core.repository.CalendarRecordRepository;
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

    private final CalendarRecordRepository repo;

    public MockScheduler(CalendarRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public void generate(Habit habit, LocalDateTime from, LocalDateTime to) {
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

        repo.save(records);
    }
}
