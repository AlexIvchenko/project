package com.github.habiteria.core.repository;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.entities.builders.Habits;
import com.github.habiteria.core.entities.builders.Users;
import com.github.habiteria.core.entities.imps.HabitImpl;
import com.github.habiteria.core.entities.imps.UserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class CalendarRecordRepositoryIT {
    @Autowired
    private CalendarRecordRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HabitRepository habitRepository;

    @Test
        public void name() throws Exception {
        UserImpl user = Users.withUsername("test")
                .withPassword("test")
                .withEmail("test")
                .withName("test", "test");
        userRepository.save(user);
        HabitImpl habit = Habits.withOwner(user)
                .withName("test")
                .withDescription("test")
                .withStart(LocalDateTime.now().minusDays(7));
        habitRepository.save(habit);
        CalendarRecord rec = new CalendarRecord();
        rec.setHabit(habit);
        rec.setStatus(Status.UNVERIFIED);
        rec.setRepeat(1);
        rec.setEndVerifying(LocalDateTime.now().minusDays(1));

        repository.save(rec);

        repository.setStatusAllRecordsEndVerifyingTimeExpired(habit, Status.FAIL, LocalDateTime.now());

        CalendarRecord found = repository.findOne(habit, 1);

        assertThat(found.getStatus()).isEqualTo(Status.FAIL);
    }
}
