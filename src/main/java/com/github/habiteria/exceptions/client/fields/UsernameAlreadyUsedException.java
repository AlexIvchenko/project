package com.github.habiteria.exceptions.client.fields;

import com.github.habiteria.dto.UserDto;

/**
 * @author Alex Ivchenko
 */
public class UsernameAlreadyUsedException extends IllegalFieldValueException {
    private static final String FIELD = "username";
    private static final String CONSTRAINT = "unique";
    private static final FieldCode CODE = new FieldCode(UserDto.class, FIELD, CONSTRAINT);

    public UsernameAlreadyUsedException(String username) {
        super("username " + username + " already used", CODE, username);
    }
}
