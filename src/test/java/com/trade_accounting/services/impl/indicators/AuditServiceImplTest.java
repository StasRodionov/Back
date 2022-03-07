package com.trade_accounting.services.impl.indicators;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.repositories.indicators.AuditRepository;
import com.trade_accounting.services.interfaces.indicators.AuditService;
import com.trade_accounting.utils.mapper.client.DepartmentMapperImpl;
import com.trade_accounting.utils.mapper.indicators.AuditMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    @Mock
    AuditServiceImpl auditService;

    @Mock
    AuditRepository auditRepository;

    @Test
    void getAll() {
        List<Audit> repoTestStub = Stream.of(
                ModelStubs.getAudit(1L),
                ModelStubs.getAudit(2L),
                ModelStubs.getAudit(3L),
                ModelStubs.getAudit(4L)
        )
                .collect(Collectors.toList());
        when(auditRepository.findAll()).thenReturn(repoTestStub);
        List<AuditDto> actual = auditService.getAll();
        assertNotNull(actual, "Expected not null");
        assertEquals(actual.size(), repoTestStub.size());
        for (int i = 0; i < repoTestStub.size(); i++) {
            assertEquals(repoTestStub.get(0).getId(), actual.get(0).getId());
        }
    }
}