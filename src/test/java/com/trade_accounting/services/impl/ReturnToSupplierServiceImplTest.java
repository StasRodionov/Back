package com.trade_accounting.services.impl;

import com.trade_accounting.repositories.ReturnToSupplierRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReturnToSupplierServiceImplTest {

    @Mock
    ReturnToSupplierRepository returnToSupplierRepository;

    @InjectMocks
    ReturnToSupplierServiceImpl returnToSupplierService;

    @Test
    public void test(){

    }

}
