package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.Correction;
import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.CorrectionDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.CorrectionProductRepository;
import com.trade_accounting.repositories.CorrectionRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.CorrectionService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;
    private final CorrectionProductRepository correctionProductRepository;
    private final DtoMapper dtoMapper;

    public CorrectionServiceImpl(CorrectionRepository correctionRepository,
                                 WarehouseRepository warehouseRepository,
                                 CompanyRepository companyRepository,
                                 CorrectionProductRepository correctionProductRepository,
                                 DtoMapper dtoMapper) {
        this.correctionRepository = correctionRepository;
        this.warehouseRepository = warehouseRepository;
        this.companyRepository = companyRepository;
        this.correctionProductRepository = correctionProductRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<CorrectionDto> getAll() {
        return correctionRepository.getAll().stream()
                .map(dtoMapper::toCorrectionDto)
                .collect(Collectors.toList());
    }

    @Override
    public CorrectionDto getById(Long id) {
        return dtoMapper.toCorrectionDto(correctionRepository.getCorrectionById(id));
    }

    @Override
    public CorrectionDto create(CorrectionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public CorrectionDto update(CorrectionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        correctionRepository.deleteById(id);
    }

    private CorrectionDto saveOrUpdate(CorrectionDto dto) {
        Correction correction = dtoMapper.toCorrection(dto);
        Warehouse warehouse = dtoMapper.warehouseDtoToWarehouse(warehouseRepository.getById(dto.getWarehouseId()));
        Company company = dtoMapper.companyDtoToCompany(companyRepository.getById(dto.getCompanyId()));

        List<CorrectionProduct> correctionProducts = dto.getCorrectionProductIds().stream()
                .map(id -> correctionProductRepository.findById(id).orElse(null)).collect(Collectors.toList());

        correction.setWarehouse(warehouse);
        correction.setCompany(company);
        correction.setCorrectionProducts(correctionProducts);

        return dtoMapper.toCorrectionDto(correctionRepository.save(correction));
    }
}

