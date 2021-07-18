package com.trade_accounting.services.impl;

import com.trade_accounting.models.MovementProduct;
import com.trade_accounting.models.dto.MovementProductDto;
import com.trade_accounting.repositories.MovementProductRepository;
import com.trade_accounting.repositories.ProductRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovementProductServiceImplTest {

    @Mock
    MovementProductRepository movementProductRepository;

    @Mock
    ProductRepository productRepository;

    @Spy
    DtoMapperImpl dtoMapper;

    @InjectMocks
    MovementProductServiceImpl movementProductService;

    @Test
    void getAll_shouldReturnFilledListMovementProduct() {
        when(movementProductRepository.findAll())
                .thenReturn(List.of(ModelStubs.getMovementProduct(1L),
                        ModelStubs.getMovementProduct(2L),
                        ModelStubs.getMovementProduct(3L)));

        List<MovementProductDto> movementProductDtos = movementProductService.getAll();
        assertEquals(3, movementProductDtos.size());
    }

    @Test
    void getById_shouldReturnFilledMovementProduct() {
        Optional<MovementProduct> movementProduct = Optional.of(ModelStubs.getMovementProduct(1L));

        when(movementProductRepository.findById(anyLong())).thenReturn(movementProduct);

        MovementProductDto movementProductDto = movementProductService.getById(1L);
        assertEquals(1L, movementProductDto.getId());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        saveOrUpdate();
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        saveOrUpdate();
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        movementProductService.deleteById(anyLong());
        verify(movementProductRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(movementProductRepository.save(any())).thenReturn(ModelStubs.getMovementProduct(1L));
        MovementProductDto movementProductDto = movementProductService.create(DtoStubs.getMovementProductDto(1L));
        assertEquals(1, movementProductDto.getId());
        verify(movementProductRepository).save(any(MovementProduct.class));
    }
}
