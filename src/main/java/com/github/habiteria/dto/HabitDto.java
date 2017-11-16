package com.github.habiteria.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
public class HabitDto {
    private String name;
    private String description;
    private ScheduleDto schedule;
}
