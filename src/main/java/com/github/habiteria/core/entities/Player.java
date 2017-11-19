package com.github.habiteria.core.entities;

/**
 * @author Alex Ivchenko
 */
public interface Player {
    User getUser();

    int getPoints();

    int getHealth();
}
