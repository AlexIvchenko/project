package com.github.netcracker2017team.project.rest.utils;

import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Resources;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
public class ResourceUtils {
    public static  <T> Resources<PersistentEntityResource> toResources(Collection<T> collection, PersistentEntityResourceAssembler asm) {
        return new Resources<>(collection.stream().map(asm::toFullResource).collect(Collectors.toSet()));
    }
}
