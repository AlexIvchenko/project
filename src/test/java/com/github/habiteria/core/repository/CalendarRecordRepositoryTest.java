package com.github.habiteria.core.repository;

import com.github.habiteria.core.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CalendarRecordRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HabitRepository habitRepository;
    @Autowired
    private CalendarRecordRepository repo;
    @Test
    public void name() throws Exception {
        User user = new User("a", null, null, "e", "p");
        userRepository.save(user);
        Habit habit = new Habit();
        habit.setOwner(user);
        habit.setSchedule(new Schedule());
        habitRepository.save(habit);

        CalendarRecord record = new CalendarRecord();
        record.setStatus(Status.UNVERIFIED);
        record.setHabit(habit);
        record.setRepeat(1);

        LocalDate date = LocalDate.now();

        record.setStartDoing(date.atStartOfDay());
        record.setEndDoing(date.plusDays(1).atStartOfDay());

        record.setStartVerifying(date.atStartOfDay());
        record.setEndVerifying(date.plusDays(1).atStartOfDay());

        repo.save(record);

        assertThat(repo.findVerifiableIn(user, LocalDateTime.now())).hasSize(1);
    }
}