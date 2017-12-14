package com.github.habiteria.core.entities;

import com.github.habiteria.core.entities.imps.HabitImpl;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "repeat", updatable = false, nullable = false)
    private int repeat;

    @Column(name = "start_doing", updatable = false, nullable = false)
    private LocalDateTime startDoing;

    @Column(name = "end_doing", updatable = false, nullable = false)
    private LocalDateTime endDoing;

    @Column(name = "start_verifying", updatable = false, nullable = false)
    private LocalDateTime startVerifying;

    @Column(name = "end_verifying", updatable = false, nullable = false)
    private LocalDateTime endVerifying;

    @Column(name = "required", updatable = false, nullable = false)
    private boolean required;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Transient
    private boolean explicit = false;

    public boolean isUnverified() {
        return status == Status.UNVERIFIED;
    }

    public LocalDate getDate() {
        return startDoing.toLocalDate();
    }

    public boolean isVerifiableIn(LocalDateTime time) {
        return !getStartVerifying().isAfter(time) && !getEndVerifying().isBefore(time);
    }
}
