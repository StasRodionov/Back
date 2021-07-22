package com.trade_accounting.services.impl;

import com.trade_accounting.models.TechnicalCard;
import com.trade_accounting.models.dto.TechnicalCardDto;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.services.impl.Stubs.ModelStubs;
import com.trade_accounting.services.impl.Stubs.dto.TechnicalCardDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.TechnicalCardModelStubs;
import com.trade_accounting.utils.DtoMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TechnicalCardServiceImplTest {

    @Spy
    private DtoMapperImpl dtoMapper;

    @Mock
    private TechnicalCardRepository technicalCardRepository;

    @InjectMocks
    private TechnicalCardServiceImpl technicalCardService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void getAll(){
            when(technicalCardRepository.findAll())
                .thenReturn(List.of(
                        TechnicalCardModelStubs.getTechnicalCard(1L),
                        TechnicalCardModelStubs.getTechnicalCard(2L),
                        TechnicalCardModelStubs.getTechnicalCard(3L)
                ));
        List<TechnicalCardDto> technicalCardDtos = technicalCardService.getAll();

        assertEquals(3,technicalCardDtos.size());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void getById() {
        when(technicalCardRepository.getOne(anyLong()))
                .thenReturn(TechnicalCardModelStubs.getTechnicalCard(1L));

        TechnicalCardDto technicalCardDto = technicalCardService.getById(1L);

        assertEquals(1, technicalCardDto.getId());
    }

    @Test
    void deleteById() {
        technicalCardService.deleteById(anyLong());
        verify(technicalCardRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
//        when(productRepository.getOne(anyLong()))
//                .thenReturn(ModelStubs.getProduct(1L));

        when(technicalCardRepository.save(any(TechnicalCard.class)))
                .thenReturn(TechnicalCardModelStubs.getTechnicalCard(1L));

        TechnicalCardDto technicalCardDto = technicalCardService
                .create(TechnicalCardDtoStubs.getDto(1L));

        assertEquals(1, technicalCardDto.getId());
        verify(technicalCardRepository).save(any(TechnicalCard.class));
    }
}
