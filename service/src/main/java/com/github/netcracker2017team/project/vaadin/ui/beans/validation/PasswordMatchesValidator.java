package com.github.netcracker2017team.project.vaadin.ui.beans.validation;

import com.github.netcracker2017team.project.vaadin.ui.beans.SignUpBean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Alex Ivchenko
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        SignUpBean user = (SignUpBean) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
