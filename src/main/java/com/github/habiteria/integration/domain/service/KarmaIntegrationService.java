package com.github.habiteria.integration.domain.service;

import com.github.habiteria.integration.domain.resources.KarmaResource;

/**
 * @author Alex Ivchenko
 */
public interface KarmaIntegrationService {
    KarmaResource getKarma(Long userId);
}
