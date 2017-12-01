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

    private static class Builder implements OwnerStageBuilder, NameStageBuilder, DescriptionStageBuilder, StartStageBuilder, TypeStageBuilder {
        private User owner;
        private String name;
        private String description;
        private LocalDateTime start;

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
        public TypeStageBuilder withStart(LocalDateTime start) {
            if (start == null) {
                start = LocalDateTime.now();
            }
            this.start = start;
            return this;
        }

        @Override
        public HabitImpl withType(Schedule.Type type) {
            ScheduleImpl schedule = new ScheduleImpl();
            schedule.setStart(start);
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
        TypeStageBuilder withStart(LocalDateTime start);
    }

    public interface TypeStageBuilder {
        HabitImpl withType(Schedule.Type type);
    }
}
