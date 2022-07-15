package com.trade_accounting.services.impl.units;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.client.DepartmentDtoStubs;
import com.trade_accounting.Stubs.dto.units.SalesChannelDtoStubs;
import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.models.dto.units.CurrencyDto;
import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.units.SalesChannel;
import com.trade_accounting.repositories.units.SalesChannelRepository;
import com.trade_accounting.services.interfaces.units.SalesChannelService;
import com.trade_accounting.utils.mapper.units.SalesChannelMapper;
import com.trade_accounting.utils.mapper.units.SalesChannelMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SalesChannelServiceImplTest {

    @Mock
    private SalesChannelRepository salesChannelRepository;

    @Spy
    SalesChannelMapperImpl salesChannelMapper;
    @InjectMocks
    private SalesChannelServiceImpl salesChannelServiceImpl;

    @Test
    void getAll_shouldReturnListFilledSalesChannelDto() {
        when(salesChannelRepository.getAll())
                .thenReturn(
                        List.of(
                                SalesChannelDtoStubs.getSalesChannelDto(1L),
                                SalesChannelDtoStubs.getSalesChannelDto(2L),
                                SalesChannelDtoStubs.getSalesChannelDto(3L)
                        )
                );

        List<SalesChannelDto> salesChannel = salesChannelServiceImpl.getAll();

        assertNotNull(salesChannel, "Failure - expected that list of salesChannel not null");
        assertTrue(salesChannel.size() > 0, "failure - expected that size of list of salesChannel greater than 0");

        for (SalesChannelDto salesChannelDto : salesChannel) {
            salesChannelDtoIsCorrectlyInited(salesChannelDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListSalesChannelDto() {
        when(salesChannelRepository.getAll())
                .thenReturn(new ArrayList<>());

        List<SalesChannelDto> salesChannel = salesChannelServiceImpl.getAll();

        assertNotNull(salesChannel, "Failure - expected that list of currency not null");
        assertEquals(0, salesChannel.size(), "failure - expected that size of list of currency equals 0");
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        salesChannelServiceImpl.create(SalesChannelDtoStubs.getSalesChannelDto(1L));

        verify(salesChannelRepository).save(any(SalesChannel.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        salesChannelServiceImpl.update(SalesChannelDtoStubs.getSalesChannelDto(1L));

        verify(salesChannelRepository).save(any(SalesChannel.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        salesChannelServiceImpl.deleteById(1L);

        verify(salesChannelRepository).deleteById(1L);
    }

    void salesChannelDtoIsCorrectlyInited(SalesChannelDto salesChannelDto) {
        assertNotNull(salesChannelDto, "failure - fail in passed salesChannelDto");
        assertNotNull(salesChannelDto.getId(), "failure - fail in field 'id' into salesChannelDto");
        assertNotNull(salesChannelDto.getName(), "failure - fail in field 'name' into salesChannelDto");
        assertNotNull(salesChannelDto.getType(), "failure - fail in field 'type' into salesChannelDto");
        assertNotNull(salesChannelDto.getDescription(), "failure - fail in field 'description' into salesChannelDto");
    }
}