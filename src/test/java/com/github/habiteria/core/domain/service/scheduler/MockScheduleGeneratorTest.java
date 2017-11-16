package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.Status;
import com.github.habiteria.core.repository.CalendarRecordRepository;
import com.github.habiteria.core.repository.HabitRepository;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import static org.mockito.Mockito.*;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class MockScheduleGeneratorTest {
    private MockScheduleGenerator generator;

    @MockBean
    private HabitRepository habitRepository;

    @MockBean
    private CalendarRecordRepository recordRepository;

    @Before
    public void setUp() throws Exception {
        generator = new MockScheduleGenerator(habitRepository, recordRepository);
    }

    @Test
    public void givenTodayStartedHabit_whenGenerate_thenGenerateRecordOnlyForToday() throws Exception {
        Habit habit = mock(Habit.class);
        Schedule schedule = mock(Schedule.class);
        when(schedule.getStart()).thenReturn(LocalDateTime.now());
        when(habit.getSchedule()).thenReturn(schedule);
        generator.generate(habit);
        verify(recordRepository, times(1)).save(argThat(new IterableMatcher<>(1)));
    }

    @Test
    public void givenWeekAgoStartedHabit_whenGenerate_thenGenerateAllMissedRecords() throws Exception {
        Habit habit = mock(Habit.class);
        Schedule schedule = mock(Schedule.class);
        when(schedule.getStart()).thenReturn(LocalDateTime.now().minusDays(7));
        when(habit.getSchedule()).thenReturn(schedule);
        generator.generate(habit);
        verify(recordRepository, times(1)).save(argThat(new IterableMatcher<>(8)));
    }

    private static class CalendarRecordCondition extends Condition<CalendarRecord> {
        private Predicate<CalendarRecord> predicate;

        private CalendarRecordCondition() {
            this.predicate = record -> true;
        }

        @Override
        public boolean matches(CalendarRecord value) {
            return predicate.test(value);
        }

        public static CalendarRecordCondition record() {
            return new CalendarRecordCondition();
        }

        public CalendarRecordCondition and(Predicate<CalendarRecord> predicate) {
            this.predicate = this.predicate.and(predicate);
            return this;
        }

        public CalendarRecordCondition unvefined() {
            this.predicate = this.predicate.and(record -> record.getStatus() == Status.UNVERIFIED);
            return this;
        }

        public CalendarRecordCondition today() {
            this.predicate = this.predicate.and(record -> record.getStartDoing().toLocalDate().isEqual(LocalDate.now()));
            return this;
        }
    }

    private static class IterableMatcher<T> extends ArgumentMatcher<Iterable<T>> {
        private final int expectedSize;

        public IterableMatcher(int size) {
            this.expectedSize = size;
        }


        @Override
        @SuppressWarnings("unchecked")
        public boolean matches(Object o) {
            int size = 0;
            Iterable<T> iterable = (Iterable<T>) o;
            for (T e : iterable) {
                size++;
            }
            return expectedSize == size;
        }
    }
}