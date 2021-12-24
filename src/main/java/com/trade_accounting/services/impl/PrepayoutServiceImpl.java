package com.trade_accounting.services.impl;


import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Prepayout;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.PrepayoutDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.PrepayoutRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.interfaces.PrepayoutService;
import com.trade_accounting.utils.mapper.PrepayoutMapper;
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
public class PrepayoutServiceImpl implements PrepayoutService {

    private final PrepayoutRepository prepayoutRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final PrepayoutMapper prepayoutMapper;

    @Override
    public List<PrepayoutDto> search(Specification<Prepayout> spec) {
        return executeSearch(prepayoutRepository, prepayoutMapper::toDto, spec);
    }

    @Override
    public List<PrepayoutDto> getAll() {
        return prepayoutRepository.findAll().stream()
                .map(prepayoutMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrepayoutDto getById(Long id) {
        Optional<Prepayout> prepayout = prepayoutRepository.findById(id);
        return prepayoutMapper.toDto(prepayout.orElse(new Prepayout()));
    }

    @Override
    public PrepayoutDto create(PrepayoutDto prepayoutDto) {
        Prepayout preayoutSaved = prepayoutMapper.toModel(prepayoutDto);
        Company company = companyRepository.getCompaniesById(prepayoutDto.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(prepayoutDto.getContractorId());
        RetailStore retailStore = retailStoreRepository.getOne(prepayoutDto.getRetailStoreId());
        preayoutSaved.setCompany(company);
        preayoutSaved.setContractor(contractor);
        preayoutSaved.setRetailStore(retailStore);

        return prepayoutMapper.toDto(prepayoutRepository.save(preayoutSaved));
    }

    @Override
    public PrepayoutDto update(PrepayoutDto prepayoutDto) {
        Prepayout payout = prepayoutRepository.save(prepayoutMapper.toModel(prepayoutDto));
        return prepayoutMapper.toDto(payout);
    }

    @Override
    public void deleteById(Long id) {
        prepayoutRepository.deleteById(id);
    }
}
