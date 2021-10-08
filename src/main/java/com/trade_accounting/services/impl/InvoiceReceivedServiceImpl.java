package com.trade_accounting.services.impl;

import com.trade_accounting.models.Acceptance;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.InvoiceReceived;
import com.trade_accounting.models.dto.InvoiceReceivedDto;
import com.trade_accounting.repositories.AcceptanceRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.InvoiceReceivedRepository;
import com.trade_accounting.services.interfaces.InvoiceReceivedService;
import com.trade_accounting.utils.mapper.InvoiceReceivedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceReceivedServiceImpl implements InvoiceReceivedService {

    private final InvoiceReceivedRepository invoiceReceivedRepository;
    private final InvoiceReceivedMapper invoiceReceivedMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final AcceptanceRepository acceptanceRepository;

    @Override
    public List<InvoiceReceivedDto> getAll() {
        return invoiceReceivedRepository.findAll().stream()
                .map(invoiceReceivedMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceReceivedDto getById(Long id) {
        Optional<InvoiceReceived> invoice = invoiceReceivedRepository.findById(id);
        return invoiceReceivedMapper.toDto(invoice.orElse(new InvoiceReceived()));
    }

    @Override
    public InvoiceReceivedDto create(InvoiceReceivedDto dto) {
        InvoiceReceived invoiceReceived = invoiceReceivedMapper.toModel(dto);
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(dto.getContractorId());
        Acceptance acceptance = acceptanceRepository.getOne(dto.getAcceptanceId());
        invoiceReceived.setCompany(company);
        invoiceReceived.setContractor(contractor);
        invoiceReceived.setAcceptance(acceptance);
        return invoiceReceivedMapper.toDto(invoiceReceivedRepository.save(invoiceReceived));
    }

    @Override
    public InvoiceReceivedDto update(InvoiceReceivedDto dto) {
        InvoiceReceived invoiceReceived = invoiceReceivedRepository.save(invoiceReceivedMapper.toModel(dto));
        return invoiceReceivedMapper.toDto(invoiceReceived);
    }

    @Override
    public void deleteById(Long id) {
        invoiceReceivedRepository.deleteById(id);
    }
}
