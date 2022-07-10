package com.trade_accounting.audit.service;

import com.trade_accounting.audit.model.Audit;
import com.trade_accounting.audit.repository.AuditRepository;
import com.trade_accounting.audit.service.impl.AuditServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuditServiceTest {
    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditServiceImpl auditService;

    private Audit audit;

    @BeforeEach
    public void setup() {
        audit = Audit.builder()
                .id(1L)
                .description("audit 1")
                .date(LocalDateTime.of(2022, 2, 24, 5, 2))
                .employeeId(1L)
                .build();
    }

    @DisplayName("JUnit test for getAll() method. Check return NotNull, size")
    @Test
    public void getAllAudit() {
        Audit audit1 = Audit.builder()
                .id(2L)
                .description("audit 1")
                .date(LocalDateTime.of(2022, 2, 24, 5, 2))
                .employeeId(2L)
                .build();

        given(auditRepository.findAll()).willReturn(List.of(audit, audit1));
        List<Audit> audits = auditService.getAll();

        assertThat(audits).isNotNull();
        assertThat(audits.size()).isEqualTo(2);
    }
}
