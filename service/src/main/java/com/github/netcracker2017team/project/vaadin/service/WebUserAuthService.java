package com.github.netcracker2017team.project.vaadin.service;

import com.github.netcracker2017team.project.vaadin.ui.beans.SignUpBean;

/**
 * @author Alex Ivchenko
 */
public interface WebUserAuthService {
    void signUp(SignUpBean signUpbean);
    void signIn(String username, String password);
}
