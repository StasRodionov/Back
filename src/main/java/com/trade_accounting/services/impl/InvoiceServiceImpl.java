package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final WarehouseRepository warehouseRepository;
    private final DtoMapper dtoMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              CompanyRepository companyRepository,
                              ContractorRepository contractorRepository,
                              WarehouseRepository warehouseRepository,
                              DtoMapper dtoMapper) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
        this.warehouseRepository = warehouseRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<InvoiceDto> search(Specification<Invoice> specification) {
        return executeSearch(invoiceRepository, dtoMapper::invoiceToInvoiceDto, specification);
    }

    @Override
    public List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice) {
        List<InvoiceDto> invoiceList = invoiceRepository.findBySearchAndTypeOfInvoice(search, typeOfInvoice);
        for (InvoiceDto invoice : invoiceList) {
            Invoice invoice1 = invoiceRepository.getOne(invoice.getId());
            Company company = companyRepository.getCompaniesById(invoice1.getCompany().getId());
            Contractor contractor = contractorRepository.getContractorById(invoice1.getContractor().getId());
            Warehouse warehouse = warehouseRepository.getOne(invoice1.getWarehouse().getId());
            invoice.setCompanyId((warehouse.getId()));
            invoice.setContractorId(contractor.getId());
            invoice.setWarehouseId(warehouse.getId());

        }
        return invoiceList;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.findAll().stream()
                .map(dtoMapper::invoiceToInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto getById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return dtoMapper.invoiceToInvoiceDto(invoice.orElse(new Invoice()));
    }

    @Override
    public InvoiceDto create(InvoiceDto invoiceDto) {
        Invoice invoiceSaved = invoiceRepository.save(dtoMapper.invoiceDtoToInvoice(invoiceDto));
        invoiceDto.setId(invoiceSaved.getId());
        return dtoMapper.invoiceToInvoiceDto(invoiceSaved);
    }


    @Override
    public InvoiceDto update(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceRepository.save(dtoMapper.invoiceDtoToInvoice(invoiceDto));
        return dtoMapper.invoiceToInvoiceDto(invoice);
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
