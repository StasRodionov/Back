package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.InvoicesStatusRepository;
import com.trade_accounting.utils.mapper.InvoicesStatusMapperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

class InvoicesStatusServiceImplTest {

    @InjectMocks
    private InvoicesStatusServiceImpl statusService;

    @Mock
    private InvoicesStatusRepository statusRepository;

    @Spy
    private InvoicesStatusMapperImpl statusMapper;

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getByName() {
    }
}