package com.trade_accounting.services.impl;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
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
        return invoiceRepository.findAll(specification).stream()
                .map(dtoMapper::invoiceToInvoiceDto).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice) {
        List<InvoiceDto> invoiceDtoList = invoiceRepository.findBySearchAndTypeOfInvoice(search, typeOfInvoice);
        for (InvoiceDto invoice: invoiceDtoList) {
            invoice.setCompanyDto(companyRepository.getById(invoice.getCompanyDto().getId()));
            invoice.setContractorDto(dtoMapper.contractorToContractorDto(
                    contractorRepository.getOne(invoice.getContractorDto().getId())));
            invoice.setWarehouseDto(warehouseRepository.getById(invoice.getWarehouseDto().getId()));
        }
        return invoiceDtoList;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.findAll().stream()
                .map(dtoMapper::invoiceToInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getAll(String typeOfInvoice) {
        return invoiceRepository.findByTypeOfInvoice(TypeOfInvoice.valueOf(typeOfInvoice)).stream()
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
        return update(invoiceDto);
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
