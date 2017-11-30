package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.builders.Habits;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Alex Ivchenko
 */
public class GeneratorImplTest {
    private GeneratorImpl generator;
    private LocalDateTime startTime;
    private LocalDate startDate;
    private Habit daily;
    private Habit weekday;
    private Habit weekend;

    @Before
    public void setUp() throws Exception {
        generator = new GeneratorImpl();
        startTime = LocalDateTime.of(2017, Month.NOVEMBER, 23, 13, 30); // Thursday
        startDate = startTime.toLocalDate();
        User user = mock(User.class);

        daily = Habits.withOwner(user)
                .withName("daily")
                .withDescription("daily habit, starts week ago")
                .withStart(startTime)
                .withType(Schedule.Type.DAILY);

        weekday = Habits.withOwner(user)
                .withName("weekday")
                .withDescription("weekday habit, starts week ago")
                .withStart(startTime)
                .withType(Schedule.Type.WEEKDAY);

        weekend = Habits.withOwner(user)
                .withName("weekend")
                .withDescription("weekend habit, starts week ago")
                .withStart(startTime)
                .withType(Schedule.Type.WEEKEND);

    }

    @Test
    public void givenDailyHabit_whenGettingAllWithOffset_thenCorrect() throws Exception {
        Set<CalendarRecord> generated = generator.getAllBetween(daily, startDate.plusDays(2), startDate.plusDays(4));
        assertThat(generated).hasSize(3);
        List<CalendarRecord> list = generated.stream()
                .sorted(Comparator.comparingInt(CalendarRecord::getRepeat))
                .collect(Collectors.toList());
        assertThat(list.get(0).getRepeat()).isEqualTo(3);
        assertThat(list.get(1).getRepeat()).isEqualTo(4);
        assertThat(list.get(2).getRepeat()).isEqualTo(5);
    }

    @Test
    public void givenWeekdayHabit_whenGettingAllWithOffset_thenCorrect() throws Exception {
        Set<CalendarRecord> generated = generator.getAllBetween(weekday, startDate.plusDays(2), startDate.plusDays(4)); // SATURDAY - MONDAY
        assertThat(generated).hasSize(1);
        List<CalendarRecord> list = generated.stream()
                .sorted(Comparator.comparingInt(CalendarRecord::getRepeat))
                .collect(Collectors.toList());
        assertThat(list.get(0).getRepeat()).isEqualTo(3);
        assertThat(list.get(0).getDate().getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    }

    @Test
    public void givenWeekendHabit_whenGettingAllWithOffset_thenCorrect() throws Exception {
        Set<CalendarRecord> generated = generator.getAllBetween(weekend, startDate.plusDays(2), startDate.plusDays(4)); // SATURDAY - MONDAY
        assertThat(generated).hasSize(2);
        List<CalendarRecord> list = generated.stream()
                .sorted(Comparator.comparingInt(CalendarRecord::getRepeat))
                .collect(Collectors.toList());
        CalendarRecord saturday = list.get(0);
        assertThat(saturday.getRepeat()).isEqualTo(1);
        assertThat(saturday.getDate().getDayOfWeek()).isEqualTo(DayOfWeek.SATURDAY);
        CalendarRecord sunday = list.get(1);
        assertThat(sunday.getRepeat()).isEqualTo(2);
        assertThat(sunday.getDate().getDayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);
    }

    @Test
    public void givenDailyHabit_whenGettingRepeat_thenCorrect() throws Exception {
        CalendarRecord first = generator.getOneRepeat(daily, 1);
        CalendarRecord second = generator.getOneRepeat(daily, 2);
        CalendarRecord third = generator.getOneRepeat(daily, 3);
        assertThat(first.getDate()).isEqualTo(startDate);
        assertThat(second.getDate()).isEqualTo(startDate.plusDays(1));
        assertThat(third.getDate()).isEqualTo(startDate.plusDays(2));
        assertThat(third.getRepeat()).isEqualTo(3);
    }

    @Test
    public void givenWeekdayHabit_whenGettingRepeat_thenCorrect() throws Exception {
        CalendarRecord thursday = generator.getOneRepeat(weekday, 1);
        CalendarRecord friday = generator.getOneRepeat(weekday, 2);
        CalendarRecord monday = generator.getOneRepeat(weekday, 3);
        assertThat(thursday.getDate()).isEqualTo(startDate);
        assertThat(thursday.getRepeat()).isEqualTo(1);
        assertThat(friday.getDate()).isEqualTo(startDate.plusDays(1));
        assertThat(friday.getRepeat()).isEqualTo(2);
        assertThat(monday.getDate()).isEqualTo(startDate.plusDays(4));
        assertThat(monday.getRepeat()).isEqualTo(3);
    }

    @Test
    public void givenWeekendHabit_whenGettingRepeat_thenCorrect() throws Exception {
        CalendarRecord saturday = generator.getOneRepeat(weekend, 1);
        CalendarRecord sunday = generator.getOneRepeat(weekend, 2);
        assertThat(saturday.getDate()).isEqualTo(startDate.plusDays(2));
        assertThat(saturday.getRepeat()).isEqualTo(1);
        assertThat(sunday.getDate()).isEqualTo(startDate.plusDays(3));
        assertThat(sunday.getRepeat()).isEqualTo(2);
    }
}