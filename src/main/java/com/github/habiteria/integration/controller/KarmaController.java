package com.github.habiteria.integration.controller;

import com.github.habiteria.integration.controller.annotations.Rest;
import com.github.habiteria.integration.domain.resources.KarmaResource;
import com.github.habiteria.integration.domain.service.KarmaIntegrationService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Alex Ivchenko
 */
@Rest
public class KarmaController {
    private final KarmaIntegrationService service;

    public KarmaController(KarmaIntegrationService service) {
        this.service = service;
    }

    @GetMapping("/users/{userId}/karma")
    public HttpEntity<KarmaResource> getKarma(@PathVariable("userId") final Long userId) {
        return new HttpEntity<>(service.getKarma(userId));
    }
}
