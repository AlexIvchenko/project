package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.service.karma.KarmaService;
import com.github.habiteria.integration.domain.assemblers.KarmaResourceAsm;
import com.github.habiteria.integration.domain.resources.KarmaResource;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class KarmaIntegrationServiceImpl implements KarmaIntegrationService {
    private final KarmaService service;
    private final KarmaResourceAsm asm;

    public KarmaIntegrationServiceImpl(KarmaService service, KarmaResourceAsm asm) {
        this.service = service;
        this.asm = asm;
    }

    @Override
    public KarmaResource getKarma(Long userId) {
        return asm.toResource(service.current(userId));
    }
}
