package com.github.netcracker2017team.project.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.Collections;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@EqualsAndHashCode(callSuper = false)
public class HabitsResource extends ResourceSupport {
    private final Set<HabitResource> habits;
    
    public HabitsResource(Set<HabitResource> habits) {
        this.habits = habits;
    }

    @JsonProperty("_embedded")
    public Set<HabitResource> getHabits() {
        return Collections.unmodifiableSet(habits);
    }
}
