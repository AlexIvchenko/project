package com.github.netcracker2017team.web.service;

import com.github.netcracker2017team.web.ui.beans.SignUpBean;

/**
 * @author Alex Ivchenko
 */
public interface UserService {
    void signUp(SignUpBean signUpbean);
    void signIn(String username, String password);
}
