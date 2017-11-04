package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.personal.PersonalStep;
import com.github.netcracker2017team.project.rest.resources.PersonalStepResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class PersonalStepResourceAssembler implements ResourceAssembler<PersonalStep, PersonalStepResource> {
    @Override
    public PersonalStepResource toResource(PersonalStep entity) {
        PersonalStepResource resource = new PersonalStepResource(entity.getTemplate().getName(), entity.getTemplate().getDescription());
        return resource;
    }
}
