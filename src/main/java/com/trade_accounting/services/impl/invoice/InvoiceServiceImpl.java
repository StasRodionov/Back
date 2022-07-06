package com.trade_accounting.services.impl.invoice;

import com.trade_accounting.models.dto.invoice.InvoiceDto;
import com.trade_accounting.models.dto.invoice.InvoiceProductDto;
import com.trade_accounting.models.dto.invoice.TypeOfOrder;
import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.dto.purchases.PurchaseCreateOrderDto;
import com.trade_accounting.models.entity.invoice.Invoice;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.invoice.InvoiceRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.invoice.InvoiceProductService;
import com.trade_accounting.services.interfaces.invoice.InvoiceService;
import com.trade_accounting.services.interfaces.purchases.PurchaseForecastService;
import com.trade_accounting.services.interfaces.warehouse.ProductService;
import com.trade_accounting.utils.mapper.company.CompanyMapper;
import com.trade_accounting.utils.mapper.company.ContractorMapper;
import com.trade_accounting.utils.mapper.invoice.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final WarehouseRepository warehouseRepository;
    private final ContractorMapper contractorMapper;
    private final CompanyMapper companyMapper;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceProductService invoiceProductService;
    private final ProductService productService;
    private final PurchaseForecastService purchaseForecastService;

    @Override
    public List<InvoiceDto> search(Specification<Invoice> specification) {
        return executeSearch(invoiceRepository, invoiceMapper::toDto, specification);
    }


    @Override
    public List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice) {
        List<InvoiceDto> invoiceDtoList = invoiceRepository.findBySearchAndTypeOfInvoice(search, typeOfInvoice);
        for (InvoiceDto invoice : invoiceDtoList) {
            invoice.setCompanyId(companyMapper.toDto(companyRepository.getCompaniesById(invoice.getCompanyId())).getId());
            invoice.setContractorId(contractorMapper.contractorToContractorDto(
                    contractorRepository.getOne(invoice.getContractorId())).getId());
            invoice.setWarehouseId(warehouseRepository.getById(invoice.getWarehouseId()).getId());

        }
        return invoiceDtoList;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getFromDateTime(LocalDateTime dateTime) {
        return invoiceRepository.getFromDateTime(dateTime);
    }

    @Override
    public List<InvoiceDto> getByContractorId(Long id) {
        return invoiceRepository.findByContractorId(id);
    }

    @Override
    public List<InvoiceDto> getByProjectId(Long id) {
        return invoiceRepository.findByProjectId(id);
    }

    @Override
    public void moveToRecyclebin(long id) {
        Invoice invoice = invoiceRepository.getOne(id);
        invoice.setIsRecyclebin(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Invoice invoice = invoiceRepository.getOne(id);
        invoice.setIsRecyclebin(false);
        invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceDto getById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoiceMapper.toDto(invoice.orElse(new Invoice()));
    }

    @Override
    public InvoiceDto create(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceRepository.save(invoiceMapper.toModel(invoiceDto));
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public InvoiceDto update(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceRepository.save(invoiceMapper.toModel(invoiceDto));
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void createAll(PurchaseCreateOrderDto purchaseCreateOrderDto) {
        List<PurchaseControlDto> purchaseControlDtoList = purchaseCreateOrderDto.getPurchaseControlDtoList();
        TypeOfOrder typeOfOrder = purchaseCreateOrderDto.getTypeOfOrder();
        if (typeOfOrder.equals(TypeOfOrder.GENERAL)) {
            // для общего заказа создается одна накладная
            Invoice invoice = createInvoice(purchaseControlDtoList.get(0), typeOfOrder);
            // без установления значения поля contractor null возникает ошибка со стороны Hibernate
            invoice.setContractor(null);

            List<InvoiceProductDto> invoiceProductDtos = getInvoiceProductDtoList(invoice.getId(), purchaseControlDtoList);
            invoiceProductService.createAll(invoiceProductDtos);
        } else if (typeOfOrder.equals(TypeOfOrder.GROUPING_BY_CONTRACTOR)) {
            //если у товаров один и тот же поставщик, для них создается одна накладная (invoiceDto)
            Map<Long, List<PurchaseControlDto>> dtoGroupedByContractorId = purchaseControlDtoList.stream()
                    .collect(
                            Collectors.groupingBy(PurchaseControlDto::getContractorId)
                    );
            List<InvoiceProductDto> invoiceProductDtos = new ArrayList<>();
            for (List<PurchaseControlDto> purchaseControlDtos : dtoGroupedByContractorId.values()) {
                Invoice invoice = createInvoice(purchaseControlDtos.get(0), typeOfOrder);
                invoiceProductDtos.addAll(getInvoiceProductDtoList(invoice.getId(), purchaseControlDtos));
            }
            invoiceProductService.createAll(invoiceProductDtos);
        }
    }

    private Invoice createInvoice(PurchaseControlDto purchaseControlDto, TypeOfOrder typeOfOrder) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setDate(String.valueOf(LocalDateTime.now()));
        invoiceDto.setCompanyId(purchaseControlDto.getCompanyId());
        if (typeOfOrder.equals(TypeOfOrder.GROUPING_BY_CONTRACTOR)) {
            invoiceDto.setContractorId(purchaseControlDto.getContractorId());
        }
        invoiceDto.setWarehouseId(purchaseControlDto.getWarehouseId());
        invoiceDto.setInvoicesStatusId(1L);
        invoiceDto.setTypeOfInvoice("EXPENSE");
        invoiceDto.setIsPrint(false);
        invoiceDto.setIsSent(false);
        invoiceDto.setIsSpend(false);
        return invoiceRepository.save(invoiceMapper.toModel(invoiceDto));
    }

    private List<InvoiceProductDto> getInvoiceProductDtoList(Long invoiceId, List<PurchaseControlDto> purchaseControlDtoList) {
        return purchaseControlDtoList.stream()
                .map(
                        x -> InvoiceProductDto.builder()
                                .invoiceId(invoiceId)
                                .productId(x.getProductNameId())
                                .price(productService.getById(x.getProductNameId()).getPurchasePrice())
                                .amount(BigDecimal.valueOf(purchaseForecastService.getById(x.getForecastId()).getOrdered()))
                                .build()
                ).collect(Collectors.toList());
    }
}
