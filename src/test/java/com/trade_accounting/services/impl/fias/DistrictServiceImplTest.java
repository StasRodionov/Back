package com.trade_accounting.services.impl.fias;

import com.trade_accounting.repositories.fias.DistrictRepository;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceImplTest {
    @Mock
    private DistrictRepository districtRepository;

    @Spy
    private DtoMapperImpl dtoMapper;

    @InjectMocks
    private DistrictServiceImpl districtService;

}
