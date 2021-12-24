package com.trade_accounting.services.impl;

import com.trade_accounting.models.BuyersReturn;
import com.trade_accounting.models.Company;
import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.BuyersReturnDto;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.BuyersReturnRepository;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.ContractorRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.BuyersReturnService;
import com.trade_accounting.utils.mapper.BuyersReturnMapper;
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
public class BuyersReturnServiceImpl implements BuyersReturnService {


    private final BuyersReturnRepository buyersReturnRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final WarehouseRepository warehouseRepository;
    private final BuyersReturnMapper buyersReturnMapper;

    @Override
    public List<BuyersReturnDto> search(Specification<BuyersReturn> spec) {
        return executeSearch(buyersReturnRepository, buyersReturnMapper::toDto, spec);
    }

    @Override
    public List<BuyersReturnDto> getAll() {
        return buyersReturnRepository.findAll().stream()
                .map(buyersReturnMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BuyersReturnDto> getByContractorId(Long id) {
        return buyersReturnRepository.findByContractorId(id);
    }

    @Override
    public BuyersReturnDto getById(Long id) {
        Optional<BuyersReturn> buyersReturn = buyersReturnRepository.findById(id);
        return buyersReturnMapper.toDto(buyersReturn.orElse(new BuyersReturn()));
    }

    @Override
    public BuyersReturnDto create(BuyersReturnDto buyersReturnDto) {
        BuyersReturn buyersReturnSaved = buyersReturnMapper.toModel(buyersReturnDto);
        Company company = companyRepository.getCompaniesById(buyersReturnDto.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(buyersReturnDto.getContractorId());
        Warehouse warehouse = warehouseRepository.getOne(buyersReturnDto.getWarehouseId());
        buyersReturnSaved.setCompany(company);
        buyersReturnSaved.setContractor(contractor);
        buyersReturnSaved.setWarehouse(warehouse);

        return buyersReturnMapper.toDto(buyersReturnRepository.save(buyersReturnSaved));
    }

    @Override
    public BuyersReturnDto update(BuyersReturnDto buyersReturnDto) {
        BuyersReturn buyersReturn = buyersReturnRepository.save(buyersReturnMapper.toModel(buyersReturnDto));
        return buyersReturnMapper.toDto(buyersReturn);
    }

    @Override
    public void deleteById(Long id) {
        buyersReturnRepository.deleteById(id);
    }

    @Override
    public List<BuyersReturnDto> findBySearch(String text) {
        List<BuyersReturnDto> buyersReturnDtoList = buyersReturnRepository.findBySearch(text);
        return buyersReturnDtoList;
    }
}
