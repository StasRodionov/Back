package com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.repositories.retail.RetailCloudCheckRepository;
import com.trade_accounting.services.interfaces.retail.RetailCloudCheckService;
import com.trade_accounting.utils.mapper.retail.RetailCloudCheckMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailCloudCheckServiceImpl implements RetailCloudCheckService {

    private final RetailCloudCheckRepository retailCloudCheckRepository;

    private final RetailCloudCheckMapper retailCloudCheckMapper;

    public RetailCloudCheckServiceImpl(RetailCloudCheckRepository retailCloudCheckRepository, RetailCloudCheckMapper retailCloudCheckMapper) {
        this.retailCloudCheckRepository = retailCloudCheckRepository;
        this.retailCloudCheckMapper = retailCloudCheckMapper;
    }

    @Override
    public List<RetailCloudCheckDto> getAll() {
        return retailCloudCheckRepository.findAll().stream()
                .map(retailCloudCheckMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailCloudCheckDto getById(Long id) {
        return retailCloudCheckMapper.toDto(retailCloudCheckRepository.getOne(id));
    }

    @Override
    public RetailCloudCheckDto create(RetailCloudCheckDto dto) {
        RetailCloudCheck retailCloudCheck = retailCloudCheckRepository.save(retailCloudCheckMapper.toModel(dto));
        if (dto.getId() == null) {
            dto.setId(retailCloudCheck.getId());
        }
        return retailCloudCheckMapper.toDto(retailCloudCheck);
    }

    @Override
    public RetailCloudCheckDto update(RetailCloudCheckDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailCloudCheckRepository.deleteById(id);
    }

    @Override
    public List<RetailCloudCheckDto> search(Specification<RetailCloudCheck> spec) {
        return executeSearch(retailCloudCheckRepository, retailCloudCheckMapper::toDto, spec);
    }
}
