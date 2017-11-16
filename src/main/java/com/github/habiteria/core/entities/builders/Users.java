package com.github.habiteria.core.entities.builders;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.imps.UserImpl;

import java.util.Objects;

/**
 * @author Alex Ivchenko
 */
public class Users {
    public static PasswordStageBuilder withUsername(String username) {
        return new Builder().withUsername(username);
    }

    private static class Builder implements UsernameStageBuilder, PasswordStageBuilder, EmailStageBuilder, NameStageBuilder {
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        @Override
        public PasswordStageBuilder withUsername(String username) {
            Objects.requireNonNull(username);
            this.username = username;
            return this;
        }

        @Override
        public EmailStageBuilder withPassword(String password) {
            Objects.requireNonNull(password);
            this.password = password;
            return this;
        }

        @Override
        public NameStageBuilder withEmail(String email) {
            Objects.requireNonNull(email);
            this.email = email;
            return this;
        }

        @Override
        public User withName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            return new UserImpl(username, firstName, lastName, email, password);
        }
    }

    public interface UsernameStageBuilder {
        PasswordStageBuilder withUsername(String username);
    }

    public interface PasswordStageBuilder {
        EmailStageBuilder withPassword(String password);
    }

    public interface EmailStageBuilder {
        NameStageBuilder withEmail(String email);
    }

    public interface NameStageBuilder {
        User withName(String firstName, String lastName);
    }
}
