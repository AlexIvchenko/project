package com.github.habiteria.core.entities;

/**
 * @author Alex Ivchenko
 */
public interface User {
    Long getId();

    String getUsername();

    String getPassword();

    String getFirstName();

    String getLastName();

    Karma getKarma();

    void changePassword(String password);
}
