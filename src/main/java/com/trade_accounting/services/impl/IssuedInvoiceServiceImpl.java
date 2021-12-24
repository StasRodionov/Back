package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.IssuedInvoice;
import com.trade_accounting.models.dto.IssuedInvoiceDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.IssuedInvoiceRepository;
import com.trade_accounting.services.interfaces.IssuedInvoiceService;
import com.trade_accounting.utils.mapper.IssuedInvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IssuedInvoiceServiceImpl implements IssuedInvoiceService {

    private final IssuedInvoiceRepository issuedInvoiceRepository;
    private final IssuedInvoiceMapper issuedInvoiceMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;

    @Override
    public List<IssuedInvoiceDto> getAll() {
        return issuedInvoiceRepository.findAll().stream()
                .map(issuedInvoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public IssuedInvoiceDto getById(Long id) {
        Optional<IssuedInvoice> invoice = issuedInvoiceRepository.findById(id);
        return issuedInvoiceMapper.toDto(invoice.orElse(new IssuedInvoice()));
    }

    @Override
    public IssuedInvoiceDto create(IssuedInvoiceDto dto) {
    IssuedInvoice issuedInvoice = issuedInvoiceMapper.toModel(dto);
    Company company = companyRepository.getCompaniesById(dto.getCompanyId());
    Contractor contractor = contractorRepository.getContractorById(dto.getContractorId());
        issuedInvoice.setCompany(company);
        issuedInvoice.setContractor(contractor);
        return issuedInvoiceMapper.toDto(issuedInvoiceRepository.save(issuedInvoice));
    }

    @Override
    public IssuedInvoiceDto update(IssuedInvoiceDto dto) {
        IssuedInvoice issuedInvoice = issuedInvoiceRepository.save(issuedInvoiceMapper.toModel(dto));
        return issuedInvoiceMapper.toDto(issuedInvoice);
    }

    @Override
    public void deleteById(Long id) {
        issuedInvoiceRepository.deleteById(id);
    }
}
