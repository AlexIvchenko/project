package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.repository.HabitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class DailyGeneratorImplTest {
    @MockBean
    private HabitRepository repo;

    private DailyGeneratorImpl generator;

    private Habit habit;
    private LocalDateTime habitStarts;

    @Before
    public void setUp() throws Exception {
        habit = mock(Habit.class);
        Schedule schedule = mock(Schedule.class);
        habitStarts = LocalDateTime.now().minusDays(7);
        when(habit.getSchedule()).thenReturn(schedule);
        when(habit.getSchedule().getStart()).thenReturn(habitStarts);
        generator = new DailyGeneratorImpl(repo);
    }

    @Test
    public void shouldGenerateRightQuantityOfRecords() throws Exception {
        Set<CalendarRecord> all = generator.getAllBetween(habit, LocalDate.now().minusDays(7), LocalDate.now());
        assertThat(all).hasSize(8);
    }

    @Test
    public void shouldGenerateRightRepeats() throws Exception {
        Set<CalendarRecord> all = generator.getAllBetween(habit, LocalDate.now(), LocalDate.now());
        assertThat(all).hasSize(1).extracting(CalendarRecord::getRepeat)
                .contains(8);
    }

    @Test
    public void shouldGenerateRightRepeat() throws Exception {
        CalendarRecord record = generator.getOneRepeat(habit, 2);
        assertThat(record.getRepeat()).isEqualTo(2);
        assertThat(record.getStartDoing().toLocalDate()).isEqualTo(habitStarts.plusDays(1).toLocalDate());
    }
}