package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.InternalOrderProductRepository;
import com.trade_accounting.repositories.InternalOrderRepository;
import com.trade_accounting.services.interfaces.InternalOrderProductService;
import com.trade_accounting.services.interfaces.InternalOrderService;
import com.trade_accounting.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InternalOrderServiceImpl implements InternalOrderService {
    private final InternalOrderRepository internalOrderRepository;
    private final InternalOrderProductRepository internalOrderProductRepository;
    private final InternalOrderProductService internalOrderProductService;
    private final CompanyRepository companyRepository;
    private final DtoMapper dtoMapper;

    @Override
    public List<InternalOrderDto> getAll() {
        return internalOrderRepository.findAll().stream()
                .map(dtoMapper::internalOrderToInternalOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public InternalOrderDto getById(Long id) {
        return dtoMapper.internalOrderToInternalOrderDto(internalOrderRepository.getOne(id));
    }

    @Override
    public InternalOrderDto create(InternalOrderDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InternalOrderDto update(InternalOrderDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        internalOrderRepository.deleteById(id);
    }

    private InternalOrderDto saveOrUpdate(InternalOrderDto dto) {
        InternalOrder internalOrder = dtoMapper.internalOrderDtoToInternalOrder(dto);

        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<InternalOrderProduct> correctionProducts = dto.getInternalOrderProductsIds().stream()
                .map(id -> internalOrderProductRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        internalOrder.setCompany(company);
        internalOrder.setDate(date);
        internalOrder.setInternalOrderProducts(correctionProducts);

        return dtoMapper.internalOrderToInternalOrderDto(internalOrderRepository.save(internalOrder));
    }
}
