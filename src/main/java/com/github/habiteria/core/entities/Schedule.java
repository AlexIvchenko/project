package com.github.habiteria.core.entities;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
public interface Schedule {
    LocalDateTime getStart();

    LocalDateTime getEnd();

    Type getType();

    enum Type {
        DAILY, WEEKEND, WEEKDAY
    }
}
