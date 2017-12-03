package com.github.habiteria.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.habiteria.core.entities.Schedule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
public class ScheduleDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;
    // TODO make other class
    private Schedule.Type type;
}
