package com.trade_accounting.services.impl.units;

import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.models.entity.units.OnlineStore;
import com.trade_accounting.repositories.units.OnlineStoreRepository;
import com.trade_accounting.services.interfaces.units.OnlineStoreService;
import com.trade_accounting.utils.mapper.units.OnlineStoreMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OnlineStoreServiceImpl implements OnlineStoreService {

    private final OnlineStoreRepository onlineStoreRepository;
    private final OnlineStoreMapper onlineStoreMapper;

    public OnlineStoreServiceImpl(OnlineStoreRepository onlineStoreRepository,
                                  OnlineStoreMapper onlineStoreMapper) {
        this.onlineStoreRepository = onlineStoreRepository;
        this.onlineStoreMapper = onlineStoreMapper;
    }


    @Override
    public List<OnlineStoreDto> getAll() {
        return onlineStoreRepository.getAll();
    }

    @Override
    public OnlineStoreDto getById(Long id) {
        return onlineStoreRepository.getById(id);
    }

    @Override
    public OnlineStoreDto create(OnlineStoreDto dto) {
        return onlineStoreMapper.toDto(onlineStoreRepository.save(onlineStoreMapper.toEntity(dto)));
    }

    @Override
    public OnlineStoreDto update(OnlineStoreDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        onlineStoreRepository.deleteById(id);
    }

    @Override
    public List<OnlineStoreDto> search(Specification<OnlineStore> spec) {
        return executeSearch(onlineStoreRepository, onlineStoreMapper::toDto, spec);
    }

    @Override
    public List<OnlineStoreDto> searchByString(String text) {
        return onlineStoreRepository.searchByString(text)
                .stream()
                .map(onlineStoreMapper::toDto)
                .collect(Collectors.toList());
    }
}
