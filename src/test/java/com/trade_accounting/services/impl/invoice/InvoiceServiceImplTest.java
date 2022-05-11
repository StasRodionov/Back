package com.trade_accounting.services.impl.invoice;

import com.trade_accounting.Stubs.dto.invoice.InvoiceDtoStubs;
import com.trade_accounting.Stubs.dto.purchases.PurchaseControlDtoStubs;
import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.models.dto.invoice.TypeOfOrder;
import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.dto.purchases.PurchaseCreateOrderDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.invoice.InvoiceRepository;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.impl.purchases.PurchaseForecastServiceImpl;
import com.trade_accounting.services.impl.warehouse.ProductServiceImpl;
import com.trade_accounting.utils.mapper.company.CompanyMapper;
import com.trade_accounting.utils.mapper.company.ContractorMapper;
import com.trade_accounting.utils.mapper.invoice.InvoiceMapperImpl;
import com.trade_accounting.utils.mapper.util.FileMapperImpl;
import com.trade_accounting.utils.mapper.util.ImageMapperImpl;
import com.trade_accounting.utils.mapper.warehouse.ProductMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.trade_accounting.Stubs.dto.purchases.PurchaseForecastDtoStubs.getPurchaseForecastDto;
import static com.trade_accounting.Stubs.model.invoice.InvoiceModelStubs.getInvoice;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {InvoiceServiceImpl.class, InvoiceProductServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private InvoiceProductServiceImpl invoiceProductService;
    @Mock
    private ProductServiceImpl productService;
    @Mock
    private PurchaseForecastServiceImpl purchaseForecastService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ContractorRepository contractorRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Spy
    private ContractorMapper contractorMapper;
    @Spy
    private CompanyMapper companyMapper;
    @Spy
    private InvoiceMapperImpl invoiceMapper;

    @Captor
    private ArgumentCaptor<Invoice> invoiceCaptor;

    @Captor
    private ArgumentCaptor<List<InvoiceProductDto>> invoiceProductDtoListCaptor;

    @Test
    void testFindBySearchAndTypeOfInvoice() {
        ArrayList<InvoiceDto> invoiceDtoList = new ArrayList<InvoiceDto>();
        when(this.invoiceRepository.findBySearchAndTypeOfInvoice(anyString(), (TypeOfInvoice) any()))
                .thenReturn(invoiceDtoList);
        List<InvoiceDto> actualFindBySearchAndTypeOfInvoiceResult = this.invoiceService
                .findBySearchAndTypeOfInvoice("Search", TypeOfInvoice.RECEIPT);
        assertSame(invoiceDtoList, actualFindBySearchAndTypeOfInvoiceResult);
        assertTrue(actualFindBySearchAndTypeOfInvoiceResult.isEmpty());
        verify(this.invoiceRepository).findBySearchAndTypeOfInvoice(anyString(), (TypeOfInvoice) any());
        assertEquals(actualFindBySearchAndTypeOfInvoiceResult, this.invoiceService.getAll());
    }


    @Test
    void getAll_shouldReturnListFilledInvoiceDto() {
        when(invoiceRepository.findAll())
                .thenReturn(
                        Stream.of(
                                getInvoice(1L),
                                getInvoice(2L),
                                getInvoice(3L)
                        ).collect(Collectors.toList())
                );
        List<InvoiceDto> invoiceDtoList = invoiceService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );
        assertTrue(
                invoiceDtoList.size() > 0,
                "failure - expected that a list of invoiceDto grater than 0"
        );
        for (InvoiceDto invoiceDto : invoiceDtoList) {
            invoiceListDtoIsCorrectlyInited(invoiceDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListInvoiceDtoList() {
        when(invoiceRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<InvoiceDto> invoiceDtoList =
                invoiceService.getAll();
        assertNotNull(
                invoiceDtoList,
                "failure - expected that a list of invoiceDto not null"
        );
        assertEquals(
                0, invoiceDtoList.size(),
                "failure - expected that size of list of invoiceDto equals 0"
        );
    }

    @Test
    void getById_shouldReturnFilledInvoiceDto() {
        Optional<Invoice> invoiceDtoFromRepo =
                Optional.of(
                        getInvoice(1L)
                );

        when(invoiceRepository.findById(anyLong()))
                .thenReturn(invoiceDtoFromRepo);

        InvoiceDto invoiceDto =
                invoiceService.getById(1L);

        assertNotNull(
                invoiceDto,
                "failure - expected that invoiceDto not null"
        );

        invoiceListDtoIsCorrectlyInited(invoiceDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        invoiceService.create(
                InvoiceDtoStubs.getInvoiceDto(1L)
        );

        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        invoiceService.update(
                InvoiceDtoStubs.getInvoiceDto(1L)
        );

        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void deleteById() {
        invoiceService.deleteById(1L);
        verify(invoiceRepository).deleteById(1L);
    }


    void invoiceListDtoIsCorrectlyInited(InvoiceDto invoiceDto) {
        assertNotNull(invoiceDto, "Fail in passed invoiceDto");
        assertNotNull(invoiceDto.getId(), "Fail in field 'id' of invoiceDto");
    }

    @Test
    void createAll_general_shouldCreateOneInvoice() {
        PurchaseCreateOrderDto purchaseCreateOrderDto = createPurchaseCreateOrderDto();
        purchaseCreateOrderDto.setTypeOfOrder(TypeOfOrder.GENERAL);
        Invoice expectedInvoice = Invoice.builder()
                .typeOfInvoice(TypeOfInvoice.EXPENSE)
                .company(Company.builder().id(1L).build())
                .warehouse(Warehouse.builder().id(1L).build())
                .contractor(Contractor.builder().id(null).build())
                .invoicesStatus(InvoicesStatus.builder().id(1L).build())
                .isSpend(false)
                .isPrint(false)
                .isSent(false)
                .build();

        when(invoiceRepository.save(any(Invoice.class))).
                thenReturn(getInvoice(1L));

        when(productService.getById(1L))
                .thenReturn(ProductDto.builder()
                        .id(1L)
                        .purchasePrice(BigDecimal.ONE)
                        .build());

        when(purchaseForecastService.getById(1L))
                .thenReturn(getPurchaseForecastDto(1L));

        invoiceService.createAll(purchaseCreateOrderDto);

        verify(invoiceRepository).save(invoiceCaptor.capture());
        Invoice actualInvoice = invoiceCaptor.getValue();
        expectedInvoice.setDate(actualInvoice.getDate());
        assertEquals(expectedInvoice, actualInvoice);
    }

    @Test
    void createAll_groupingByContractor_shouldCreateTwoInvoice() {
        PurchaseCreateOrderDto purchaseCreateOrderDto = createPurchaseCreateOrderDto();
        purchaseCreateOrderDto.setTypeOfOrder(TypeOfOrder.GROUPING_BY_CONTRACTOR);

        Invoice expectedInvoice1 = Invoice.builder()
                .typeOfInvoice(TypeOfInvoice.EXPENSE)
                .company(Company.builder().id(1L).build())
                .warehouse(Warehouse.builder().id(1L).build())
                .contractor(Contractor.builder().id(1L).build())
                .invoicesStatus(InvoicesStatus.builder().id(1L).build())
                .isSpend(false)
                .isPrint(false)
                .isSent(false)
                .build();

        Invoice expectedInvoice2 = Invoice.builder()
                .typeOfInvoice(TypeOfInvoice.EXPENSE)
                .company(Company.builder().id(1L).build())
                .warehouse(Warehouse.builder().id(1L).build())
                .contractor(Contractor.builder().id(2L).build())
                .invoicesStatus(InvoicesStatus.builder().id(1L).build())
                .isSpend(false)
                .isPrint(false)
                .isSent(false)
                .build();

        when(invoiceRepository.save(any(Invoice.class))).
                thenReturn(getInvoice(1L));

        when(productService.getById(1L))
                .thenReturn(ProductDto.builder()
                        .id(1L)
                        .purchasePrice(BigDecimal.ONE)
                        .build());

        when(purchaseForecastService.getById(1L))
                .thenReturn(getPurchaseForecastDto(1L));

        invoiceService.createAll(purchaseCreateOrderDto);

        verify(invoiceRepository, times(2)).save(invoiceCaptor.capture());
        Invoice actualInvoice1 = invoiceCaptor.getAllValues().get(0);
        Invoice actualInvoice2 = invoiceCaptor.getAllValues().get(1);
        expectedInvoice1.setDate(actualInvoice1.getDate());
        expectedInvoice2.setDate(actualInvoice2.getDate());
        assertEquals(expectedInvoice1, actualInvoice1);
        assertEquals(expectedInvoice2, actualInvoice2);
    }

    @Test
    void createAll_shouldCreateListOfInvoiceProductDto() {
        PurchaseCreateOrderDto purchaseCreateOrderDto = createPurchaseCreateOrderDto();
        purchaseCreateOrderDto.setTypeOfOrder(TypeOfOrder.GENERAL);

        when(invoiceRepository.save(any(Invoice.class))).
                thenReturn(getInvoice(1L));

        when(productService.getById(1L))
                .thenReturn(ProductDto.builder()
                        .id(1L)
                        .purchasePrice(BigDecimal.ONE)
                        .build());

        when(purchaseForecastService.getById(1L))
                .thenReturn(getPurchaseForecastDto(1L));

        invoiceService.createAll(purchaseCreateOrderDto);

        InvoiceProductDto invoiceProductDto = InvoiceProductDto.builder()
                .invoiceId(1L)
                .productId(1L)
                .price(BigDecimal.ONE)
                .amount(BigDecimal.valueOf(10L))
                .build();
        List<InvoiceProductDto> expectedList = List.of(invoiceProductDto, invoiceProductDto, invoiceProductDto);

        verify(invoiceProductService).createAll(invoiceProductDtoListCaptor.capture());
        assertIterableEquals(expectedList, invoiceProductDtoListCaptor.getValue());
    }



    private PurchaseCreateOrderDto createPurchaseCreateOrderDto() {
        PurchaseControlDto dto1 = PurchaseControlDtoStubs.getDto(1L);
        dto1.setContractorId(1L);
        PurchaseControlDto dto2 = PurchaseControlDtoStubs.getDto(2L);
        dto2.setContractorId(1L);
        PurchaseControlDto dto3 = PurchaseControlDtoStubs.getDto(3L);
        dto3.setContractorId(2L);
        List<PurchaseControlDto> list = new ArrayList<>() {{
            add(dto1);
            add(dto2);
            add(dto3);
        }};
        return PurchaseCreateOrderDto.builder()
                .purchaseControlDtoList(list)
                .build();
    }

}
