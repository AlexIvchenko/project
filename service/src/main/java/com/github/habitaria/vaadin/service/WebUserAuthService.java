package com.github.habitaria.vaadin.service;

import com.github.habitaria.vaadin.ui.beans.SignUpBean;

/**
 * @author Alex Ivchenko
 */
public interface WebUserAuthService {
    void signUp(SignUpBean signUpbean);
    void signIn(String username, String password);
}
