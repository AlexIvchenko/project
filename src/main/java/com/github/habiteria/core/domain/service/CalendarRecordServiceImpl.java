package com.github.habiteria.core.domain.service;

import com.github.habiteria.core.domain.service.fetcher.StrictFetcher;
import com.github.habiteria.core.domain.service.scheduler.Scheduler;
import com.github.habiteria.core.entities.CalendarRecord;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class CalendarRecordServiceImpl implements CalendarRecordService {
    private final StrictFetcher fetcher;
    private final Scheduler scheduler;

    public CalendarRecordServiceImpl(StrictFetcher fetcher, Scheduler scheduler) {
        this.fetcher = fetcher;
        this.scheduler = scheduler;
    }

    @Override
    public CalendarRecord getRecord(Long habitId, int repeat) {
        return scheduler.getRecord(fetcher.fetchHabit(habitId), repeat);
    }
}
