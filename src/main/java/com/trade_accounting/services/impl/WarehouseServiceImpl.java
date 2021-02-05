package com.trade_accounting.services.impl;

import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.WarehouseService;
import com.trade_accounting.util.SortNumberConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<WarehouseDto> getAll() {
        return warehouseRepository.getAll();
    }

    @Override
    public WarehouseDto getById(Long id) {
        return warehouseRepository.getById(id);
    }

    @Override
    public void create(WarehouseDto warehouseDto) {
        warehouseRepository.save(
                new Warehouse(
                        warehouseDto.getName(),
                        SortNumberConverter.convert(warehouseDto.getSortNumber()),
                        warehouseDto.getAddress(),
                        warehouseDto.getCommentToAddress(),
                        warehouseDto.getComment()
                )
        );
    }

    @Override
    public void update(WarehouseDto warehouseDto) {
        warehouseRepository.save(
                new Warehouse(
                        warehouseDto.getId(),
                        warehouseDto.getName(),
                        SortNumberConverter.convert(warehouseDto.getSortNumber()),
                        warehouseDto.getAddress(),
                        warehouseDto.getCommentToAddress(),
                        warehouseDto.getComment()
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        warehouseRepository.deleteById(id);
    }
}
