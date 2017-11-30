package com.github.habiteria.integration.domain.assemblers;

import com.github.habiteria.core.entities.Karma;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.KarmaResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Alex Ivchenko
 */
@Component
public class KarmaResourceAsm implements ResourceAssembler<Karma, KarmaResource> {
    @Override
    public KarmaResource toResource(Karma entity) {
        KarmaResource resource = new KarmaResource(entity.getValue(), entity.getActualTime());
        Long userId = entity.getOwner().getId();
        resource.add(Links.updateKarma(userId));
        return resource;
    }
}
