package com.trade_accounting.services.impl;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.services.interfaces.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CompanyRepository companyRepository, ContractorRepository contractorRepository) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public List<InvoiceDto> getAll() {

        List<InvoiceDto> listInvoiceDto = invoiceRepository.getAll();
        for (InvoiceDto invoiceDto : listInvoiceDto) {
            invoiceDto.setCompanyDto(companyRepository.getById(invoiceDto.getCompanyDto().getId()));
        }
        for (InvoiceDto invoiceDto : listInvoiceDto) {
            invoiceDto.setContractorDto(contractorRepository.getById(invoiceDto.getContractorDto().getId()));
        }
        return listInvoiceDto;
    }

    @Override
    public InvoiceDto getById(Long id) {

        InvoiceDto invoiceDto = invoiceRepository.getById(id);
        invoiceDto.setCompanyDto(companyRepository.getById(invoiceDto.getCompanyDto().getId()));
        invoiceDto.setContractorDto(contractorRepository.getById(invoiceDto.getContractorDto().getId()));
        return invoiceDto;
    }

    @Override
    public void create(InvoiceDto invoiceDto) {
        invoiceRepository.save(
                new Invoice(
                        invoiceDto.getDate(),
                        invoiceDto.getTypeOfInvoice(),
                        companyRepository.getOne(invoiceDto.getCompanyDto().getId()),
                        contractorRepository.getOne(invoiceDto.getContractorDto().getId())));
    }

    @Override
    public void update(InvoiceDto invoiceDto) {
        invoiceRepository.save(
                new Invoice(
                        invoiceDto.getId(),
                        invoiceDto.getDate(),
                        invoiceDto.getTypeOfInvoice(),
                        companyRepository.getOne(invoiceDto.getCompanyDto().getId()),
                        contractorRepository.getOne(invoiceDto.getContractorDto().getId()),
                        invoiceDto.isSpend()));
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
