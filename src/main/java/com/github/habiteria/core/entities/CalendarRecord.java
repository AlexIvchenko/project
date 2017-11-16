package com.github.habiteria.core.entities;

import com.github.habiteria.core.entities.imps.HabitImpl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@Entity
@ToString(of = "habit", callSuper = false)
@Table(name = "calendar_records")
@EqualsAndHashCode(of = {"habit", "repeat"}, callSuper = false)
public class CalendarRecord extends AbstractEntity {

    @ManyToOne(targetEntity = HabitImpl.class)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    private int repeat;

    @Column(name = "start_doing")
    private LocalDateTime startDoing;

    @Column(name = "end_doing")
    private LocalDateTime endDoing;

    @Column(name = "start_verifying")
    private LocalDateTime startVerifying;

    @Column(name = "end_verifying")
    private LocalDateTime endVerifying;

    @Column(name = "required")
    private boolean required;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
