package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.domain.service.CalendarRecordService;
import com.github.habiteria.integration.domain.assemblers.CalendarRecordResAsm;
import com.github.habiteria.integration.domain.resources.CalendarRecordResource;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class CalendarRecordIntegrationServiceImpl implements CalendarRecordIntegrationService {
    private final CalendarRecordService service;
    private final CalendarRecordResAsm asm;

    public CalendarRecordIntegrationServiceImpl(CalendarRecordService service, CalendarRecordResAsm asm) {
        this.service = service;
        this.asm = asm;
    }

    @Override
    public CalendarRecordResource getRecord(Long habitId, int repeat) {
        return asm.toResource(service.getRecord(habitId, repeat));
    }
}
