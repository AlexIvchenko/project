package com.github.habiteria.core.entities.builders;

import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.HabitImpl;
import com.github.habiteria.core.entities.imps.ScheduleImpl;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public class Habits {
    public static NameStageBuilder withOwner(User owner) {
        return new Builder().withOwner(owner);
    }

    private static class Builder implements OwnerStageBuilder, NameStageBuilder, DescriptionStageBuilder, StartStageBuilder, TypeStageBuilder, EndStageBuilder {
        private User owner;
        private String name;
        private String description;
        private LocalDateTime start;
        private LocalDateTime end;

        @Override
        public NameStageBuilder withOwner(User owner) {
            Objects.requireNonNull(owner);
            this.owner = owner;
            return this;
        }

        @Override
        public DescriptionStageBuilder withName(String name) {
            Objects.requireNonNull(name);
            this.name = name;
            return this;
        }

        @Override
        public StartStageBuilder withDescription(String description) {
            Objects.requireNonNull(description);
            this.description = description;
            return this;
        }

        @Override
        public EndStageBuilder withStart(LocalDateTime start) {
            if (start == null) {
                start = LocalDateTime.now();
            }
            this.start = start;
            return this;
        }

        @Override
        public TypeStageBuilder withEnd(LocalDateTime end) {
            this.end = end;
            return this;
        }

        @Override
        public HabitImpl withType(Schedule.Type type) {
            ScheduleImpl schedule = new ScheduleImpl();
            schedule.setStart(start);
            schedule.setEnd(end);
            schedule.setType(type);
            return new HabitImpl(owner, name, description, schedule);
        }
    }

    public interface OwnerStageBuilder {
        NameStageBuilder withOwner(User owner);
    }

    public interface NameStageBuilder {
        DescriptionStageBuilder withName(String name);
    }

    public interface DescriptionStageBuilder {
        StartStageBuilder withDescription(String description);
    }

    public interface StartStageBuilder {
        EndStageBuilder withStart(LocalDateTime start);
    }

    public interface EndStageBuilder {
        TypeStageBuilder withEnd(LocalDateTime end);
    }

    public interface TypeStageBuilder {
        HabitImpl withType(Schedule.Type type);
    }
}
