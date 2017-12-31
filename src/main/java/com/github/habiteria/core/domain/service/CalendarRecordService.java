package com.github.habiteria.core.domain.service;

import com.github.habiteria.core.entities.CalendarRecord;

/**
 * @author Alex Ivchenko
 */
public interface CalendarRecordService {
    CalendarRecord getRecord(Long habitId, int repeat);
}
