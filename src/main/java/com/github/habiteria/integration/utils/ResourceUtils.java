package com.github.habiteria.integration.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class ResourceUtils {
    public static  <E, R extends ResourceSupport> Resources<R> toResources(Collection<E> collection, ResourceAssembler<E, R> asm) {
        return new Resources<>(toResourceSet(collection, asm));
    }

    public static <E, R extends ResourceSupport> Set<R> toResourceSet(Collection<E> collection, ResourceAssembler<E, R> asm) {
        return collection.stream().map(asm::toResource).collect(Collectors.toSet());
    }
}
