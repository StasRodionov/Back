package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.interfaces.RetailStoreService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailStoreServiceImpl implements RetailStoreService {

    private final RetailStoreRepository retailStoreRepository;
    private final DtoMapper dtoMapper;

    public RetailStoreServiceImpl(RetailStoreRepository retailStoreRepository, DtoMapper dtoMapper) {
        this.retailStoreRepository = retailStoreRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<RetailStoreDto> getAll() {
        return retailStoreRepository.findAll().stream()
                .map(dtoMapper::retailStoreToRetailStoreDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailStoreDto getById(Long id) {
        Optional<RetailStore> retailStore = retailStoreRepository.findById(id);
        if(retailStore.isEmpty()){
            throw new NotFoundEntityException("No retailStore ");
        }
        return dtoMapper.retailStoreToRetailStoreDto(retailStore.get());
    }

    @Override
    public void create(RetailStoreDto retailStoreDto) {
        RetailStore retailStore = ModelDtoConverter.convertToRetailStore(retailStoreDto);
        retailStoreRepository.save(retailStore);
    }

    @Override
    public void update(RetailStoreDto retailStoreDto) {
        create(retailStoreDto);
    }

    @Override
    public void deleteById(Long id) {
        retailStoreRepository.deleteById(id);
    }
}
