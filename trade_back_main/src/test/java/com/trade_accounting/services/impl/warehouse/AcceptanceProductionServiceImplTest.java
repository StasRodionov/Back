package com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.warehouse.AcceptanceProductionDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.AcceptanceProductionModelStubs;
import com.trade_accounting.models.dto.warehouse.AcceptanceProductionDto;
import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceProductionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AcceptanceProductionServiceImplTest {
    @Mock
    AcceptanceProductionRepository acceptanceProductionRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    AcceptanceProductionMapper acceptanceProductionMapper;


    @InjectMocks
    AcceptanceProductionServiceImpl acceptanceProductionService;

    @Test
    void getAll_shouldReturnListFilledAcceptanceProductionDto() {
        when(acceptanceProductionRepository.findAll())
                .thenReturn(
                        List.of(
                                AcceptanceProductionModelStubs.getAcceptanceProduction(1L),
                                AcceptanceProductionModelStubs.getAcceptanceProduction(2L),
                                AcceptanceProductionModelStubs.getAcceptanceProduction(3L)
                        )
                );

        List<AcceptanceProductionDto> acceptanceProductions = acceptanceProductionService.getAll();

        assertEquals(3, acceptanceProductions.size());
    }

    @Test
    void getById_shouldReturnFilledAcceptanceProduction() {
        when(acceptanceProductionRepository.getOne(anyLong()))
                .thenReturn(AcceptanceProductionModelStubs.getAcceptanceProduction(1L));
        when(acceptanceProductionMapper.toDto(AcceptanceProductionModelStubs.getAcceptanceProduction(1L))).thenReturn(getAcceptanceProductionDto());
        AcceptanceProductionDto acceptanceProductionDto = acceptanceProductionService.getById(1L);

        acceptanceProductionDtoIsCorrectlyInited(acceptanceProductionDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        AcceptanceProductionDto acceptanceProductionDto = AcceptanceProductionDtoStubs.getAcceptanceProductionDto(1L);
        when(acceptanceProductionMapper.toModel(acceptanceProductionDto)).thenReturn(getAcceptanceProduction());
        acceptanceProductionService.create(acceptanceProductionDto);
        verify(acceptanceProductionRepository).save(any(AcceptanceProduction.class));

    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
         AcceptanceProductionDto acceptanceProductionDto = AcceptanceProductionDtoStubs.getAcceptanceProductionDto(1L);
        when(acceptanceProductionMapper.toModel(acceptanceProductionDto)).thenReturn(getAcceptanceProduction());
        acceptanceProductionService.update(acceptanceProductionDto);
        verify(acceptanceProductionRepository).save(any(AcceptanceProduction.class));



    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        acceptanceProductionService.deleteById(anyLong());
        verify(acceptanceProductionRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(acceptanceProductionRepository.save(any(AcceptanceProduction.class)))
                .thenReturn(AcceptanceProductionModelStubs.getAcceptanceProduction(1L));

        when(productRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getProduct(1L));

        AcceptanceProductionDto acceptanceProductionDto =
                acceptanceProductionService.create(AcceptanceProductionDtoStubs.getAcceptanceProductionDto(1L));

        assertEquals(1, acceptanceProductionDto.getId());

        verify(acceptanceProductionRepository).save(any(AcceptanceProduction.class));
    }

    private void acceptanceProductionDtoIsCorrectlyInited(AcceptanceProductionDto acceptanceProductionDto) {
        assertNotNull(acceptanceProductionDto, "failure - fail in passed acceptanceProductionDto");
        assertNotNull(acceptanceProductionDto.getId(), "failure - fail in field 'id' into acceptanceProductionDto");
        assertNotNull(acceptanceProductionDto.getAmount(), "failure - fail in field 'amount' into acceptanceProductionDto");
        assertNotNull(acceptanceProductionDto.getProductId(), "failure - fail in field 'productId' into acceptanceProductionDto");
        assertNotNull(acceptanceProductionDto.getAcceptanceId(), "failure - fail in field 'acceptanceId' into acceptanceProductionDto");
        assertNotNull(acceptanceProductionDto.getPrice(), "failure - fail in field 'Price' into acceptanceProductionDto");
    }

    private AcceptanceProductionDto getAcceptanceProductionDto() {
        return new AcceptanceProductionDto(0L,
                2L,
                3L,
                4L,
                new BigDecimal(5));
    }

    private AcceptanceProduction getAcceptanceProduction() {
        return new AcceptanceProduction(0L,
                new BigDecimal(1),
                2L,
                new Product(),
                new Acceptance());
    }
}
