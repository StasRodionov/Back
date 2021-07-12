package com.trade_accounting.services.impl;

import com.trade_accounting.models.AgentReports;
import com.trade_accounting.models.dto.AgentReportsDto;
import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.services.impl.Stubs.DtoStubs;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgentReportsServiceImplTest {

    @InjectMocks
    private AgentReportsServiceImpl service;

    @Mock
    private AgentReportsRepository repository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @Test
    void repositoryIsNotNullTest() {
        assertNotNull(repository, "Repository is not filled");
    }

    @Test
    void getAllTest() {
        when(repository.findAll()).thenReturn(
                Stream.of(ModelStubs.getAgentReports(1L),
                        ModelStubs.getAgentReports(2L),
                        ModelStubs.getAgentReports(3L)).collect(Collectors.toList())
        );
        List<AgentReportsDto> list = service.getAll();
        assertEquals(3, list.size());
    }

    @Test
    void getByIdTest() {
        when(repository.findById(anyLong()))
                .thenReturn(ofNullable(ModelStubs.getAgentReports(1L)));
        AgentReportsDto dto = service.getById(1L);
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        verify(repository).findById(anyLong());
    }

    @Test
    void createTest() {
        when(repository.save(any()))
                .thenReturn(ModelStubs.getAgentReports(1L));
        AgentReportsDto dto = service.create(DtoStubs.getAgentReportsDto(1L));
        assertNotNull(dto);
        assertEquals(1, dto.getId());
        verify(repository).save(any(AgentReports.class));
    }

    @Test
    void updateTest() {
        when(repository.save(any()))
                .thenReturn(ModelStubs.getAgentReports(anyLong()));
        AgentReportsDto dto = service.update(DtoStubs.getAgentReportsDto(1L));
        assertNotNull(dto);
        verify(repository).save(any(AgentReports.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }

}
