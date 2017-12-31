package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.builders.Habits;
import com.github.habiteria.core.entities.builders.Users;
import com.github.habiteria.core.entities.imps.HabitImpl;
import com.github.habiteria.core.entities.imps.UserImpl;
import org.junit.Before;
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

    private UserImpl user;
    private HabitImpl habit;
    private CalendarRecord today;
    private CalendarRecord yesterday;
    private CalendarRecord tomorrow;

    @Before
    public void setUp() throws Exception {
        user = Users.withUsername("Test")
                .withPassword("test")
                .withEmail("test")
                .withName("test", "test");
        userRepository.save(user);
        habit = Habits.withOwner(user)
                .withName("habit")
                .withDescription("habit")
                .withStart(LocalDateTime.now())
                .withEnd(LocalDateTime.now().plusDays(10))
                .withType(Schedule.Type.DAILY);

        habitRepository.save(habit);

        LocalDate now = LocalDate.now();
        today = record(now);
        yesterday = record(now.minusDays(1));
        tomorrow = record(now.plusDays(1));

        repo.save(yesterday);
        repo.save(today);
        repo.save(tomorrow);
    }

    @Test
    public void givenRecords_whenFindVerifiableNow_whenCorrect() throws Exception {
        assertThat(repo.findVerifiableIn(user, LocalDateTime.now())).hasSize(1);
    }

    @Test
    public void givenRecords_whenFindLastRecord_whenCorrect() throws Exception {
        CalendarRecord lastRecord = repo.getLastRecord(habit);
        assertThat(lastRecord).isEqualTo(tomorrow);
    }

    private CalendarRecord record(LocalDate date) {
        CalendarRecord record = new CalendarRecord();
        record.setStatus(Status.UNVERIFIED);
        record.setHabit(habit);
        record.setRepeat(1);

        record.setStartDoing(date.atStartOfDay());
        record.setEndDoing(date.plusDays(1).atStartOfDay());

        record.setStartVerifying(date.atStartOfDay());
        record.setEndVerifying(date.plusDays(1).atStartOfDay());

        return record;
    }
}