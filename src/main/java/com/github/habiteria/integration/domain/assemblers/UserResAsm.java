package com.github.habiteria.integration.domain.assemblers;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.integration.domain.resources.UserResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class UserResAsm implements ResourceAssembler<User, UserResource> {
    @Override
    public UserResource toResource(User entity) {
        return new UserResource(entity.getUsername(), entity.getFirstName(), entity.getLastName());
    }
}
