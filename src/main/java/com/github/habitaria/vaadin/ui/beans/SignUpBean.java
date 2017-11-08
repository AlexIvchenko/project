package com.github.habitaria.vaadin.ui.beans;

import com.github.habitaria.vaadin.ui.beans.validation.NotEmptyString;
import com.github.habitaria.vaadin.ui.beans.validation.PasswordMatches;
import com.github.habitaria.vaadin.ui.beans.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Alex Ivchenko
 */
@PasswordMatches
public @Data class SignUpBean {
    @NotNull
    @NotEmptyString
    @Size(min = 6, max = 30, message = "{Size.User.Username}")
    private String username;

    @NotNull
    @NotEmptyString
    @Size(min = 6, max = 30, message = "{validation.error.size}")
    private String firstName;

    @NotNull
    @NotEmptyString
    @Size(min = 6, max = 30, message = "{validation.error.size}")
    private String lastName;

    @NotNull
    @NotEmptyString(message = "{NotEmpty.doer.password}")
    @Size(min = 6, max = 30, message = "{validation.error.size}")
    private String password;

    @Size(min = 6, max = 30, message = "{validation.error.size}")
    @NotEmptyString
    private String confirmPassword;

    @NotNull
    @ValidEmail(message = "{ValidEmail.User.email}")
    @NotEmptyString
    private String email;
}

