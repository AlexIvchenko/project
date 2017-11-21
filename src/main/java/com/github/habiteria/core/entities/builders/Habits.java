package com.github.habiteria.core.entities.builders;

import com.github.habiteria.core.entities.imps.ScheduleImpl;
import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.HabitImpl;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public class Habits {
    public static NameStageBuilder withOwner(User owner) {
        return new Builder().withOwner(owner);
    }

    private static class Builder implements OwnerStageBuilder, NameStageBuilder, DescriptionStageBuilder, StartStageBuilder {
        private User owner;
        private String name;
        private String description;

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
        public HabitImpl withStart(LocalDateTime start) {
            if (start == null) {
                start = LocalDateTime.now();
            }
            ScheduleImpl schedule = new ScheduleImpl();
            schedule.setStart(start);
            HabitImpl habit = new HabitImpl(owner, name, description, schedule);
            schedule.setHabit(habit);
            return habit;
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
        HabitImpl withStart(LocalDateTime start);
    }
}
