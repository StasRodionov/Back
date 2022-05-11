package com.trade_accounting.services.impl.invoice;

import com.trade_accounting.Stubs.dto.invoice.InvoiceProductDtoStubs;
import com.trade_accounting.Stubs.model.invoice.InvoiceProductModelStubs;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.repositories.invoice.InvoiceProductRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.utils.mapper.invoice.InvoiceProductMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceProductServiceImplTest {

    @InjectMocks
    private InvoiceProductServiceImpl invoiceProductService;

    @Mock
    private InvoiceProductRepository invoiceProductRepository;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private InvoiceProductMapperImpl invoiceProductMapper;

    @Captor
    private ArgumentCaptor<List<InvoiceProduct>> invoiceProductListCaptor;

    @Test
    void getAll_shouldReturnListFilledInvoiceProductDto() {
        when(invoiceProductRepository.findAll())
                .thenReturn(
                        Stream.of(
                                InvoiceProductModelStubs.getInvoiceProduct(1L),
                                InvoiceProductModelStubs.getInvoiceProduct(2L),
                                InvoiceProductModelStubs.getInvoiceProduct(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceProductDto> invoiceProductDtoList = invoiceProductService.getAll();
        assertNotNull(
                invoiceProductDtoList,
                "failure - expected that a list of invoiceProductDto not null"
        );
        assertTrue(
                invoiceProductDtoList.size() > 0,
                "failure - expected that a list of invoiceProductDto grater than 0"
        );
        for (InvoiceProductDto invoiceProductDto : invoiceProductDtoList) {
            invoiceProductListDtoIsCorrectlyInited(invoiceProductDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceProductDtoList() {
        when(invoiceProductRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<InvoiceProductDto> invoiceDtoList =
                invoiceProductService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceProductDto not null"
        );
        assertEquals(
                0, invoiceDtoList.size(),
                "failure - expected that size of list of invoiceProductDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledInvoiceProductDto() {
        Optional<InvoiceProduct> invoiceProductDtoFromRepo =
                Optional.of(
                        InvoiceProductModelStubs.getInvoiceProduct(1L)
                );

        when(invoiceProductRepository.findById(anyLong()))
                .thenReturn(invoiceProductDtoFromRepo);

        InvoiceProductDto invoiceProductDto =
                invoiceProductService.getById(1L);

        assertNotNull(
                invoiceProductDto,
                "failure - expected that invoiceDto not null"
        );

        invoiceProductListDtoIsCorrectlyInited(invoiceProductDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceProductService.create(
                InvoiceProductDtoStubs.getInvoiceProductDto(1L)
        );

        verify(invoiceProductRepository).save(any(InvoiceProduct.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceProductService.update(
                InvoiceProductDtoStubs.getInvoiceProductDto(1L)
        );
        verify(invoiceProductRepository).save(any(InvoiceProduct.class));
    }

    @Test
    void deleteById() {
        invoiceProductService.deleteById(1L);
        verify(invoiceProductRepository).deleteById(1L);
    }

    void invoiceProductListDtoIsCorrectlyInited(InvoiceProductDto invoiceProductDto) {
        assertNotNull(invoiceProductDto, "Fail in passed invoiceDto");
        assertNotNull(invoiceProductDto.getId(), "Fail in field 'id' of invoiceDto");
    }

    @Test
    void createAll() {
        invoiceProductService.createAll(createInvoiceProductDtoList());

        verify(invoiceProductRepository).saveAll(invoiceProductListCaptor.capture());
        assertEquals(createInvoiceProductList(), invoiceProductListCaptor.getValue());
    }

    private List<InvoiceProductDto> createInvoiceProductDtoList() {
        InvoiceProductDto invoiceProductDto1 = InvoiceProductDto.builder()
                .invoiceId(1L)
                .productId(1L)
                .price(BigDecimal.ONE)
                .amount(BigDecimal.valueOf(10L))
                .build();

        InvoiceProductDto invoiceProductDto2 = InvoiceProductDto.builder()
                .invoiceId(2L)
                .productId(2L)
                .price(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(5L))
                .build();

        InvoiceProductDto invoiceProductDto3 = InvoiceProductDto.builder()
                .invoiceId(3L)
                .productId(3L)
                .price(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(3L))
                .build();

        return List.of(invoiceProductDto1, invoiceProductDto2, invoiceProductDto3);
    }

    private List<InvoiceProduct> createInvoiceProductList() {
        InvoiceProduct invoiceProduct1 = InvoiceProduct.builder()
                .invoice(Invoice.builder().id(1L).build())
                .product(Product.builder().id(1L).build())
                .price(BigDecimal.ONE)
                .amount(BigDecimal.valueOf(10L))
                .build();
        InvoiceProduct invoiceProduct2 = InvoiceProduct.builder()
                .invoice(Invoice.builder().id(2L).build())
                .product(Product.builder().id(2L).build())
                .price(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(5L))
                .build();
        InvoiceProduct invoiceProduct3 = InvoiceProduct.builder()
                .invoice(Invoice.builder().id(3L).build())
                .product(Product.builder().id(3L).build())
                .price(BigDecimal.TEN)
                .amount(BigDecimal.valueOf(3L))
                .build();

        return List.of(invoiceProduct1, invoiceProduct2, invoiceProduct3);
    }
}
