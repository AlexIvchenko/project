package com.github.habiteria.domain.model;

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
        @JsonSubTypes.Type(value = DailyChecker.class, name = "DAILY"),
        @JsonSubTypes.Type(value = WeeklyChecker.class, name = "WEEKLY")
})
@Entity
@Table(name = "habit_checkers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HabitChecker extends AbstractEntity {

    public HabitChecker() {
    }

    public HabitChecker(Habit habit, CheckerType type) {
        this.habit = habit;
        this.type = type;
    }

    public HabitChecker(CheckerType type) {
        this.type = type;
    }

    @OneToOne
    private Habit habit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @JsonProperty("type")
    private CheckerType type;
}
