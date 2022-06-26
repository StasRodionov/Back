package com.trade_accounting.services.impl.util;

import com.trade_accounting.models.dto.util.DiscountDto;
import com.trade_accounting.repositories.util.DiscountRepository;
import com.trade_accounting.services.interfaces.util.DiscountService;
import com.trade_accounting.utils.mapper.util.DiscountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    DiscountMapper discountMapper;

    @Override
    public List<DiscountDto> getAll() {
        return discountRepository.findAll().stream()
                .map(discountMapper::discountToDiscountDto)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDto getById(Long id) {
        return discountMapper.discountToDiscountDto(discountRepository.getOne(id));
    }

    @Override
    public DiscountDto create(DiscountDto dto) {
        return discountMapper.discountToDiscountDto(discountRepository.save(discountMapper.discountDtoToDiscount(dto)));
    }

    @Override
    public DiscountDto update(DiscountDto dto) {
        return discountMapper.discountToDiscountDto(discountRepository.save(discountMapper.discountDtoToDiscount(dto)));
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }
}
