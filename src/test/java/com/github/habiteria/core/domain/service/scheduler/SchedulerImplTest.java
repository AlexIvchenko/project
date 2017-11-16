package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.domain.service.scheduler.exceptions.FutureScheduleRetreivingException;
import com.github.habiteria.core.domain.service.scheduler.exceptions.SequenceOfRepeatsBrokenException;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.repository.CalendarRecordRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class SchedulerImplTest {
    @MockBean
    private ScheduleGenerator generator;

    @MockBean
    private CalendarRecordRepository recordRepository;

    private SchedulerImpl scheduler;

    @Before
    public void setUp() throws Exception {
        scheduler = new SchedulerImpl(recordRepository, generator);
    }

    @Test
    public void whenInvokeFindVerifiable_thenMustInvokeGenerator() throws Exception {
        User user = mock(User.class);
        scheduler.findVerifiable(user);
        verify(generator).generateAll(user);
    }

    @Test
    public void whenInvokeGetRecord_thenMustInvokeGenerator() throws Exception {
        Habit habit = mock(Habit.class);
        when(recordRepository.findOne(habit, 1)).thenReturn(mock(CalendarRecord.class));
        scheduler.getRecord(habit, 1);
        verify(generator).generate(habit);
    }

    @Test
    public void whenGettingRecords_thenMustInvokeGenerator() throws Exception {
        Habit habit = mock(Habit.class);
        scheduler.getRecords(habit, LocalDate.now().minusDays(1), LocalDate.now());
        verify(generator).generate(habit);
    }

    @Test(expected = FutureScheduleRetreivingException.class)
    public void givenFutureDate_whenGettingRecords_thenFail() throws Exception {
        Habit habit = mock(Habit.class);
        scheduler.getRecords(habit, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
    }

    @Test(expected = FutureScheduleRetreivingException.class)
    public void givenFutureRepeat_whenGettingRecord_thenFail() throws Exception {
        Habit habit = mock(Habit.class);
        scheduler.getRecord(habit, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenZeroRepeat_whenGettingRecord_thenFail() throws Exception {
        Habit habit = mock(Habit.class);
        scheduler.getRecord(habit, 0);
    }

    @Test(expected = SequenceOfRepeatsBrokenException.class)
    public void givenBrokenRepeatsSequence_whenFindRecordWhichNotExists_thenSequenceErrorDetected() throws Exception {
        Habit habit = mock(Habit.class);
        CalendarRecord last = mock(CalendarRecord.class);
        when(last.getRepeat()).thenReturn(2);
        when(recordRepository.findOne(habit, 1)).thenReturn(null);
        when(recordRepository.getLastRecord(habit)).thenReturn(last);
        scheduler.getRecord(habit, 1);
    }

    @Test
    public void givenCorrectData_whenGettingRecord_thenOk() throws Exception {
        Habit habit = mock(Habit.class);
        int repeat = 1;
        CalendarRecord rec1 = mock(CalendarRecord.class);
        when(recordRepository.findOne(habit, repeat)).thenReturn(rec1);
        scheduler.getRecord(habit, repeat);
        verify(recordRepository).findOne(habit, repeat);
    }
}