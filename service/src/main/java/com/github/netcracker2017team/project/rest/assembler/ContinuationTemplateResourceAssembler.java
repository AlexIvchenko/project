package com.github.netcracker2017team.project.rest.assembler;

import com.github.netcracker2017team.project.domain.model.template.user.UserContinuationTemplate;
import com.github.netcracker2017team.project.rest.resources.ContinuationTemplateResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class ContinuationTemplateResourceAssembler implements ResourceAssembler<UserContinuationTemplate, ContinuationTemplateResource> {
    @Override
    public ContinuationTemplateResource toResource(UserContinuationTemplate entity) {
        ContinuationTemplateResource resource = new ContinuationTemplateResource(entity.getName(), entity.getDescription());
        return resource;
    }
}
