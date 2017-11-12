package com.github.habiteria.core.domain.calendar;

import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Alex Ivchenko
 */
public class Calendar {
    private final Habit habit;
    private final LocalDate start;
    private final LocalDate end;
    private final Set<CalendarRecord> records;

    public Calendar(Habit habit, LocalDate start, LocalDate end, Set<CalendarRecord> records) {
        this.habit = habit;
        this.start = start;
        this.end = end;
        this.records = records;
    }

    public Habit getHabit() {
        return habit;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Set<CalendarRecord> getRecords() {
        return records;
    }
}
