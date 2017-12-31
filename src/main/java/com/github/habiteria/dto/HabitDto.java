package com.github.habiteria.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
public class HabitDto {
    private String name;
    private String description;
    private ScheduleDto schedule;
}
