package com.github.habiteria.vaadin.service;

import com.github.habiteria.vaadin.ui.beans.SignUpBean;

/**
 * @author Alex Ivchenko
 */
public interface WebUserAuthService {
    void signUp(SignUpBean signUpbean);
    void signIn(String username, String password);
}
