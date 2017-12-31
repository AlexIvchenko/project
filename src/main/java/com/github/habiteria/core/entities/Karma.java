package com.github.habiteria.core.entities;

import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
public interface Karma {
    User getOwner();

    Integer getValue();

    LocalDateTime getActualTime();

    void update(Integer value, LocalDateTime time);
}
