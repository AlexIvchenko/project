package com.github.habiteria.core.entities;

/**
 * @author Alex Ivchenko
 */
public interface User {
    String getId();

    String getUsername();

    String getPassword();

    String getFirstName();

    String getLastName();

    void changePassword(String password);
}
