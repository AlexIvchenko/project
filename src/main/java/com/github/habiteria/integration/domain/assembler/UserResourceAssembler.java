package com.github.habiteria.integration.domain.assembler;

import com.github.habiteria.core.model.User;
import com.github.habiteria.integration.domain.resources.UserResource;
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
