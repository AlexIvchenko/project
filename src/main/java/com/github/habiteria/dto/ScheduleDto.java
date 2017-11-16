package com.github.habiteria.dto;

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
}
