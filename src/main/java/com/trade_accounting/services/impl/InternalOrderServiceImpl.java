package com.trade_accounting.services.impl;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.InternalOrderProduct;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.InternalOrderDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.InternalOrderProductRepository;
import com.trade_accounting.repositories.InternalOrderRepository;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.InternalOrderService;
import com.trade_accounting.utils.mapper.InternalOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InternalOrderServiceImpl implements InternalOrderService {
    private final InternalOrderRepository internalOrderRepository;
    private final InternalOrderProductRepository internalOrderProductRepository;
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;
    private final InternalOrderMapper internalOrderMapper;

    @Override
    public List<InternalOrderDto> getAll() {
        return internalOrderRepository.findAll().stream()
                .map(internalOrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InternalOrderDto> getAll(String searchItem) {
        if ("null".equals(searchItem) || searchItem.isEmpty()) {
            List<InternalOrder> all = internalOrderRepository.findAll();
            return all.stream().map(internalOrderMapper::toDto).collect(Collectors.toList());
        } else {
            List<InternalOrder> list = internalOrderRepository.search(searchItem);
            return list.stream().map(internalOrderMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public void moveToRecyclebin(long id) {
        InternalOrder internalOrder = internalOrderRepository.getById(id);
        internalOrder.setIsRecyclebin(true);
        internalOrderRepository.save(internalOrder);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        InternalOrder internalOrder = internalOrderRepository.getById(id);
        internalOrder.setIsRecyclebin(false);
        internalOrderRepository.save(internalOrder);
    }

    @Override
    public InternalOrderDto getById(Long id) {
        return internalOrderMapper.toDto(internalOrderRepository.getOne(id));
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
        InternalOrder internalOrder = internalOrderMapper.toModel(dto);

        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Warehouse warehouse = warehouseRepository.getOne(dto.getWarehouseId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<InternalOrderProduct> internalOrderProducts = dto.getInternalOrderProductsIds().stream()
                .map(id -> internalOrderProductRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        internalOrder.setCompany(company);
        internalOrder.setWarehouse(warehouse);
        internalOrder.setDate(date);
        internalOrder.setInternalOrderProducts(internalOrderProducts);

        return internalOrderMapper.toDto(internalOrderRepository.save(internalOrder));
    }

    @Override
    public List<InternalOrderDto> search(Specification<InternalOrder> spec) {
        List<InternalOrder> internalOrderList = internalOrderRepository.findAll(spec);

        List<InternalOrderDto> internalOrderDtoList = new ArrayList<>();
        for(InternalOrder io : internalOrderList) {
            internalOrderDtoList.add(internalOrderMapper.toDto(io));
        }
        return internalOrderDtoList;
    }
}
