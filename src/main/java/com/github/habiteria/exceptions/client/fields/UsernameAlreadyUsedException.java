package com.github.habiteria.exceptions.client.fields;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alex Ivchenko
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String username) {
        super("username " + username + " already used");
    }
}
