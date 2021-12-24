package com.trade_accounting.services.impl;

import com.trade_accounting.models.RetailMaking;
import com.trade_accounting.models.dto.RetailMakingDto;
import com.trade_accounting.repositories.RetailMakingRepository;
import com.trade_accounting.services.interfaces.RetailMakingService;
import com.trade_accounting.utils.mapper.RetailMakingMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailMakingServiceImpl implements RetailMakingService {

    private final RetailMakingRepository retailMakingRepository;

    private final RetailMakingMapper retailMakingMapper;

    public RetailMakingServiceImpl(RetailMakingRepository retailMakingRepository,
                                   RetailMakingMapper retailMakingMapper) {
        this.retailMakingRepository = retailMakingRepository;
        this.retailMakingMapper = retailMakingMapper;
    }

    @Override
    public List<RetailMakingDto> getAll() {
        return retailMakingRepository.findAll().stream()
                .map(retailMakingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailMakingDto getById(Long id) {
        return retailMakingMapper.toDto(retailMakingRepository.getOne(id));
    }

    @Override
    public RetailMakingDto create(RetailMakingDto dto) {
        RetailMaking retailMaking = retailMakingRepository.save(retailMakingMapper.toModel(dto));
        if (dto.getId() == null) {
            dto.setId(retailMaking.getId());
        }
        return retailMakingMapper.toDto(retailMaking);
    }

    @Override
    public RetailMakingDto update(RetailMakingDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailMakingRepository.deleteById(id);

    }

    @Override
    public List<RetailMakingDto> search(Specification<RetailMaking> spec) {
        return executeSearch(retailMakingRepository, retailMakingMapper::toDto, spec);
    }
}
