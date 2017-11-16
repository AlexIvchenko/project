package com.github.habiteria.core.entities;

/**
 * @author Alex Ivchenko
 */
public interface Habit {
    Long getId();

    Schedule getSchedule();

    User getOwner();

    String getName();

    String getDescription();
}
