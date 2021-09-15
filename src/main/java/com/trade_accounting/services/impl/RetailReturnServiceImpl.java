package com.trade_accounting.services.impl;

import com.trade_accounting.models.RetailReturn;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailReturnDto;
import com.trade_accounting.repositories.RetailReturnRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.interfaces.RetailReturnService;
import com.trade_accounting.utils.mapper.RetailReturnMapper;
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
public class RetailReturnServiceImpl implements RetailReturnService {

    private final RetailReturnRepository retailReturnRepository;
    private final RetailReturnMapper retailReturnMapper;
    private final RetailStoreRepository retailStoreRepository;

    @Override
    public List<RetailReturnDto> getAll() {
        return retailReturnRepository.findAll().stream()
                .map(retailReturnMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailReturnDto getById(Long id) {
        Optional<RetailReturn> retailReturn = retailReturnRepository.findById(id);
        return retailReturnMapper.toDto(retailReturn.orElse(new RetailReturn()));
    }

    @Override
    public RetailReturnDto create(RetailReturnDto dto) {
        RetailReturn retailReturnSaved = retailReturnMapper.toModel(dto);
        RetailStore retailStore = retailStoreRepository.getOne(dto.getRetailStoreId());
        retailReturnSaved.setRetailStore(retailStore);

        return retailReturnMapper.toDto(retailReturnRepository.save(retailReturnSaved));
    }

    @Override
    public RetailReturnDto update(RetailReturnDto dto) {
        RetailReturn retailReturn = retailReturnRepository.save(retailReturnMapper.toModel(dto));
        return retailReturnMapper.toDto(retailReturn);
    }

    @Override
    public void deleteById(Long id) {
        retailReturnRepository.deleteById(id);
    }

    @Override
    public List<RetailReturnDto> search(Specification<RetailReturn> spec) {
        return executeSearch(retailReturnRepository, retailReturnMapper::toDto, spec);
    }
}
