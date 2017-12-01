package com.github.habiteria.dto;

import com.github.habiteria.core.entities.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
public class ScheduleDto {
    private LocalDateTime start;
    private LocalDateTime end;
    // TODO make other class
    private Schedule.Type type;
}
