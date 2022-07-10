package com.trade_accounting.audit.util;

import com.trade_accounting.audit.model.Audit;
import com.trade_accounting.audit.model.AuditDto;
import com.trade_accounting.audit.service.interfaces.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActiveMQListener {
    private final AuditService auditService;

    public ActiveMQListener(AuditService auditService) {
        this.auditService = auditService;
    }

    @JmsListener(destination = "auditCreateOrUpdateQueue")
    public void receive(AuditDto auditDto) {
        log.info("Получен от брокера: \n" + auditDto.toString());
        Audit audit = Audit.builder()
                .id(auditDto.getId())
                .description(auditDto.getDescription())
                .date(DateParser.fromStringFieldInDto(auditDto.getDate()))
                .employeeId(auditDto.getEmployeeId())
                .build();
        log.info(audit.toString());
        auditService.create(audit);
    }
}
