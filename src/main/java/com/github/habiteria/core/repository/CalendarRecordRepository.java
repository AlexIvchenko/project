package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface CalendarRecordRepository extends CrudRepository<CalendarRecord, Long> {

    @Query("select rec from CalendarRecord rec where rec.habit = :habit and rec.repeat = :repeat")
    CalendarRecord findOne(@Param("habit") Habit habit, @Param("repeat") int repeat);

    @Query("select rec from CalendarRecord rec where rec.habit.owner = :user and " +
            "(:time between rec.startVerifying and rec.endVerifying)")
    Set<CalendarRecord> findVerifiableIn(@Param("user") User user, @Param("time") LocalDateTime time);

    @Query("select rec from CalendarRecord rec " +
            "where rec.habit = :habit and rec.startDoing = (select max(rec2.startDoing) from CalendarRecord rec2)")
    CalendarRecord getLastRecord(@Param("habit") Habit habit);

    @Query("select rec from CalendarRecord rec " +
            "where rec.habit = :habit and (" +
            "(:start between rec.startDoing and rec.endDoing) or " +
            "(:finish between rec.startDoing and rec.endDoing) or " +
            "(rec.startDoing between :start and :finish and rec.endDoing between :start and :finish))")
    Set<CalendarRecord> findBetween(@Param("habit") Habit habit,
                                    @Param("start") LocalDateTime from,
                                    @Param("finish") LocalDateTime to);
    @Modifying(clearAutomatically = true)
    @Query("update CalendarRecord rec set rec.status=:status where rec.habit=:habit and rec.endVerifying < :time")
    void setStatusAllRecordsBeforeEndVerifyingTime(@Param("habit") Habit habit, @Param("status") Status status, @Param("time") LocalDateTime endVerifying);
}
