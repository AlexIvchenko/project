package com.github.habiteria.integration.assembler;

import com.github.habiteria.domain.model.User;
import com.github.habiteria.integration.resources.UserResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource> {
    @Override
    public UserResource toResource(User entity) {
        return new UserResource(entity.getUsername(), entity.getFirstName(), entity.getLastName());
    }
}
