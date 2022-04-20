package com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.warehouse.SerialNumbersDtoStubs;
import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.SerialNumbersRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.utils.mapper.warehouse.SerialNumbersMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class SerialNumbersServiceImplTest {
    @InjectMocks
    private SerialNumbersServiceImpl serialNumbersService;
    @Mock
    SerialNumbersRepository serialNumbersRepository;

    @Spy
    SerialNumbersMapper serialNumbersMapper;

    @Mock
    SerialNumbers serialNumbers;

    @Mock
    ProductRepository productRepository;

    @Mock
    WarehouseRepository warehouseRepository;


    @Test
    void getAll() {
        when(serialNumbersRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.serialNumbers(1L),
                                ModelStubs.serialNumbers(2L),
                                ModelStubs.serialNumbers(3L)
                        ).collect(Collectors.toList())
                );

        List<SerialNumbersDto> serialNumbersDtoList = serialNumbersService.getAll();

        assertNotNull(serialNumbersDtoList, "Failure - expected that list of serialNumbers not null");
        assertEquals(3, serialNumbersDtoList.size(), "failure - expected that size of list of serialNumbers equals 3");
        verify(serialNumbersRepository).findAll();

    }

    @Test
    void getById() {
        when(serialNumbersRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.serialNumbers(1L));
        when(serialNumbersMapper.toDto(ModelStubs.serialNumbers(1L))).thenReturn(getSerialNumbersDto());

        SerialNumbersDto serialNumbersDto = serialNumbersService.getById(1L);

        serialNumbersDtoIsCorrectlyInited(serialNumbersDto);
    }


    @Test
    void create() {
        SerialNumbersDto serialNumbersDto = SerialNumbersDtoStubs.getDto(1L);
        when(serialNumbersMapper.toModel(serialNumbersDto)).thenReturn(getSerialNumbers());

        serialNumbersService.create(serialNumbersDto);

        verify(serialNumbersRepository).save(any(SerialNumbers.class));
    }

    @Test
    void update() {
        SerialNumbersDto serialNumbersDto = SerialNumbersDtoStubs.getDto(1L);
        when(serialNumbersMapper.toModel(serialNumbersDto)).thenReturn(getSerialNumbers());

        serialNumbersService.update(serialNumbersDto);

        verify(serialNumbersRepository).save(any(SerialNumbers.class));
    }

    @Test
    void deleteById() {
        serialNumbersService.deleteById(1L);
        verify(serialNumbersRepository).deleteById(anyLong());
    }

    @Test
    void search() {
        when(serialNumbersRepository.findAll(Mockito.<Specification<SerialNumbers>>any()))
                .thenReturn(new ArrayList<>());

        List<SerialNumbersDto> serialNumbersDtoList = serialNumbersService
                .search(SpecificationStubs.getSerialNumbersSpecificationStub());

        assertNotNull(serialNumbersDtoList, "failure - expected that a list of serialNumbersDtoList not null");
        assertEquals(0, serialNumbersDtoList.size(), "failure - expected that size of list of serialNumbersDtoList equals 0");
    }

    @Test
    void searchByFilter() {
        when(serialNumbersRepository.findAll(Mockito.<Specification<SerialNumbers>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.serialNumbers(1L),
                                ModelStubs.serialNumbers(2L),
                                ModelStubs.serialNumbers(3L)
                        ).collect(Collectors.toList())
                );

        List<SerialNumbersDto> serialNumbersDtoList = serialNumbersService
                .search(SpecificationStubs.getSerialNumbersSpecificationStub());

        assertNotNull(serialNumbersDtoList, "failure - expected that a list of serialNumbersDtoList not null");
        assertTrue(serialNumbersDtoList.size() > 0, "failure - expected that a list of serialNumbersDtoList greater than 0");

    }

    private void serialNumbersDtoIsCorrectlyInited(SerialNumbersDto serialNumbersDto) {
        assertNotNull(serialNumbersDto, "failure - fail in passed serialNumbersDto");
        assertNotNull(serialNumbersDto.getId(), "failure - fail in field 'id' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getCode(), "failure - fail in field 'Code' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getVendorCode(), "failure - fail in field 'VendorCode' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getProductId(), "failure - fail in field 'ProductId' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getWarehouseId(), "failure - fail in field 'WarehouseId' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getTypeDocument(), "failure - fail in field 'TypeDocument' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getDocumentNumber(), "failure - fail in field 'DocumentNumber' into serialNumbersDto");
        assertNotNull(serialNumbersDto.getDescription(), "failure - fail in field 'Description' into serialNumbersDto");
    }

    private SerialNumbers getSerialNumbers() {
        return new SerialNumbers(0L,
                1L,
                2L,
                new Product(),
                new Warehouse(),
                "type",
                5L,
                "description");
    }

    private SerialNumbersDto getSerialNumbersDto() {
        return new SerialNumbersDto(0L,
                1L,
                2L,
                3L,
                4L,
                "type",
                5L,
                "description");
    }
}