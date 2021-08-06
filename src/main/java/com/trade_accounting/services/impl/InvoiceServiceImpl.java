package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contract;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.utils.mapper.CompanyMapper;
import com.trade_accounting.utils.mapper.ContractorMapper;
import com.trade_accounting.utils.mapper.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public List<InvoiceDto> search(Specification<Invoice> specification) {
        return executeSearch(invoiceRepository, invoiceMapper::toDto, specification);
    }

    @Override
    public List<InvoiceDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice) {
        List<InvoiceDto> invoiceDtoList = invoiceRepository.findBySearchAndTypeOfInvoice(search, typeOfInvoice);
        for (InvoiceDto invoice : invoiceDtoList) {
            Invoice invoice1 = invoiceMapper.toModel(invoice);
            Company company = companyRepository.getCompaniesById(invoice.getCompanyId());
            Contractor contractor = contractorRepository.getContractorById(invoice.getContractorId());
            Warehouse warehouse = warehouseRepository.getOne(invoice.getWarehouseId());
            invoice.setCompanyId(companyMapper.toDto(company).getId());
            invoice.setContractorId(contractorMapper.contractorToContractorDto(contractor).getId());
            invoice.setWarehouseId(warehouse.getId());

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
    public InvoiceDto getById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoiceMapper.toDto(invoice.orElse(new Invoice()));
    }

    @Override
    public InvoiceDto create(InvoiceDto invoiceDto) {
        Invoice invoiceSaved = invoiceRepository.save(invoiceMapper.toModel(invoiceDto));
        invoiceDto.setId(invoiceSaved.getId());

        return invoiceMapper.toDto(invoiceSaved);
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
}
