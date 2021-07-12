package com.trade_accounting.services.impl;


import com.trade_accounting.repositories.AgentReportsRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AgentReportsServiceImplTest {

    @InjectMocks
    private AgentReportsServiceImpl service;

    @Mock
    private AgentReportsRepository repository;

    @Spy
    private DtoMapperImpl dtoMapper;

}
