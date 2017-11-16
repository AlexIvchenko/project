package com.github.habiteria.core.domain.service.habit;

import com.github.habiteria.core.domain.service.fetcher.StrictFetcher;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class TrackerImplTest {
    @MockBean
    private Scheduler scheduler;

    @MockBean
    private StrictFetcher fetcher;

    private TrackerImpl tracker;


    @Before
    public void setUp() throws Exception {
        tracker = new TrackerImpl(fetcher, scheduler);
    }

    @Test
    public void givenUnverifiedHabit_whenPerform_whenOk() throws Exception {
        Habit habit = mock(Habit.class);
        CalendarRecord rec = mock(CalendarRecord.class);
        when(scheduler.getRecord(habit, 1)).thenReturn(rec);
        when(rec.getStatus()).thenReturn(Status.UNVERIFIED);
        when(rec.getStartVerifying()).thenReturn(LocalDateTime.now().minusMinutes(1));
        when(rec.getEndVerifying()).thenReturn(LocalDateTime.now().plusMinutes(1));
        when(fetcher.fetchHabit(0L)).thenReturn(habit);
        when(scheduler.getRecord(habit, 1)).thenReturn(rec);
        tracker.perform(0L, 1);

        verify(fetcher, times(1)).fetchHabit(0L);
        verify(scheduler, times(1)).getRecord(habit, 1);
    }

    // TODO add transition checking tests

    // TODO add bounds checking tests
}