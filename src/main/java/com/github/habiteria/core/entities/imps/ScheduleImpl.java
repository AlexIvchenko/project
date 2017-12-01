package com.github.habiteria.core.entities.imps;

import com.github.habiteria.core.entities.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Embeddable
public class ScheduleImpl implements Schedule {

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Schedule.Type type;

    public ScheduleImpl() {
    }
}
