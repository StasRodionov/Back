package com.trade_accounting.services.impl;


import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
