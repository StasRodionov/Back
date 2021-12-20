package com.trade_accounting.services.impl;

import com.trade_accounting.models.PrepaymentReturn;
import com.trade_accounting.models.dto.PrepaymentReturnDto;
import com.trade_accounting.repositories.PrepaymentReturnRepository;
import com.trade_accounting.services.interfaces.PrepaymentReturnService;
import com.trade_accounting.utils.mapper.PrepaymentReturnMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PrepaymentReturnServiceImpl implements PrepaymentReturnService {

    private final PrepaymentReturnMapper prepaymentReturnMapper;
    private final PrepaymentReturnRepository prepaymentReturnRepository;

    @Override
    public List<PrepaymentReturnDto> getAll() {
        return prepaymentReturnRepository.findAll().stream()
                .map(prepaymentReturnMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrepaymentReturnDto getById(Long id) {
        return prepaymentReturnMapper.toDto(prepaymentReturnRepository.findById(id).orElse(new PrepaymentReturn()));
    }

    @Override
    public PrepaymentReturnDto create(PrepaymentReturnDto dto) {
        PrepaymentReturn prepaymentReturn = prepaymentReturnRepository.save(prepaymentReturnMapper.toModel(dto));
        dto.setId(prepaymentReturn.getId());
        return dto;
    }

    @Override
    public PrepaymentReturnDto update(PrepaymentReturnDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        prepaymentReturnRepository.deleteById(id);
    }

    @Override
    public List<PrepaymentReturnDto> search(Specification<PrepaymentReturn> spec) {
        return executeSearch(prepaymentReturnRepository, prepaymentReturnMapper::toDto, spec);
    }
}
