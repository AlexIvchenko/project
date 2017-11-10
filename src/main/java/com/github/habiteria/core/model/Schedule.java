package com.github.habiteria.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DailySchedule.class, name = "DAILY"),
        @JsonSubTypes.Type(value = WeeklySchedule.class, name = "WEEKLY")
})
@Entity
@Table(name = "habit_checkers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Schedule extends AbstractEntity {

    public Schedule() {
    }

    public Schedule(Habit habit, ScheduleType type) {
        this.habit = habit;
        this.type = type;
    }

    public Schedule(ScheduleType type) {
        this.type = type;
    }

    @OneToOne(mappedBy = "schedule", optional = false)
    private Habit habit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @JsonProperty("type")
    private ScheduleType type;
}
