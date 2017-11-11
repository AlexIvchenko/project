package com.github.habiteria.core.repository;

import com.github.habiteria.core.model.CalendarRecord;
import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface CalendarRecordRepository extends CrudRepository<CalendarRecord, String> {

    @Query("select rec from CalendarRecord rec where rec.habit = :habit and rec.repeat = :repeat")
    CalendarRecord findOne(@Param("habit") Habit habit, @Param("repeat") int repeat);

    @Query("select rec from CalendarRecord rec where rec.habit.owner = :user and " +
            "(:time between rec.startVerifying and rec.endVerifying)")
    Set<CalendarRecord> findVerifiableIn(@Param("user") User user, @Param("time") LocalDateTime time);
}
