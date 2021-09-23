package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.OrdersOfProduction;
import com.trade_accounting.models.dto.OrdersOfProductionDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.OrdersOfProductionRepository;
import com.trade_accounting.repositories.TechnicalCardRepository;
import com.trade_accounting.services.interfaces.OrdersOfProductionService;
import com.trade_accounting.utils.mapper.OrdersOfProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersOfProductionServiceImpl implements OrdersOfProductionService {

    private final OrdersOfProductionRepository ordersOfProductionRepository;
    private final OrdersOfProductionMapper ordersOfProductionMapper;
    private final CompanyRepository companyRepository;
    private final TechnicalCardRepository technicalCardRepository;

    @Override
    public List<OrdersOfProductionDto> search(Specification<OrdersOfProduction> spec) {
        return executeSearch(ordersOfProductionRepository, ordersOfProductionMapper::toDto, spec);
    }

    @Override
    public List<OrdersOfProductionDto> getAll() {
        return ordersOfProductionRepository.findAll().stream()
                .map(ordersOfProductionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersOfProductionDto getById(Long id) {
        return ordersOfProductionMapper.toDto(ordersOfProductionRepository.getOne(id));
    }

    @Override
    public OrdersOfProductionDto create(OrdersOfProductionDto dto) {
        return null;
    }

    @Override
    public OrdersOfProductionDto update(OrdersOfProductionDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        ordersOfProductionRepository.deleteById(id);
    }

    @Override
    public List<OrdersOfProductionDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            return ordersOfProductionRepository.findAll().stream()
                    .map(ordersOfProductionMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return ordersOfProductionRepository.search(searchTerm).stream()
                    .map(ordersOfProductionMapper::toDto)
                    .collect(Collectors.toList());
        }
    }
}
