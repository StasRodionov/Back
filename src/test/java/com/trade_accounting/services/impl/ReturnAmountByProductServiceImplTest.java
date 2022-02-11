package com.trade_accounting.services.impl;
import com.trade_accounting.repositories.ReturnAmountByProductRepository;
import com.trade_accounting.utils.mapper.ReturnAmountByProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ReturnAmountByProductServiceImplTest {
    @Mock
    ReturnAmountByProductRepository returnAmountByProductRepository;

    @Spy
    ReturnAmountByProductMapper returnAmountByProductMapper;

    @InjectMocks
    ReturnAmountByProductServiceImpl returnAmountByProductService;

    @Test
    void getRepoFilledNotNull() {
        assertNotNull(returnAmountByProductRepository);
    }
}
