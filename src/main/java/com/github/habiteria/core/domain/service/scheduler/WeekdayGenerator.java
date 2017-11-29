package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.utils.LocalDateRange;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service("weekdayGenerator")
public class WeekdayGenerator extends AbstractGenerator {
    public WeekdayGenerator(HabitRepository habitRepository) {
        super(habitRepository);
    }

    @Override
    protected boolean isSupported(Schedule schedule) {
        return schedule.getType() == Schedule.Type.WEEKDAY;
    }

    @Override
    public Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) {
        LocalDate habitStarts = habit.getSchedule().getStart().toLocalDate();
        int repeat = 1;
        DayGenerator generator = new DayGenerator();
        HashSet<CalendarRecord> records = new HashSet<>();
        for (LocalDate date : new LocalDateRange(habitStarts, to)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                repeat++;
                if (!date.isBefore(from)) {
                    CalendarRecord record = generator.generate(habit, date, repeat);
                    records.add(record);
                }
            }
        }
        return records;
    }
}
