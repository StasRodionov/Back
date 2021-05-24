package com.trade_accounting.services.impl;

import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.dto.WarehouseDto;
import com.trade_accounting.repositories.WarehouseRepository;
import com.trade_accounting.services.interfaces.WarehouseService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.SortNumberConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    private final DtoMapper dtoMapper;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, DtoMapper dtoMapper) {
        this.warehouseRepository = warehouseRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<WarehouseDto> getAll() {
        return warehouseRepository.findAll().stream()
                .map(dtoMapper::warehouseToWarehouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseDto getById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        return dtoMapper.warehouseToWarehouseDto(
                warehouse.orElse(
                        new Warehouse()
                )
        );
    }

    @Override
    public WarehouseDto create(WarehouseDto warehouseDto) {
        Warehouse warehouse = dtoMapper.warehouseDtoToWarehouse(warehouseDto);
        warehouse.setSortNumber(
                SortNumberConverter.convert(warehouseDto.getSortNumber())
        );
       // addressDto.setId(addressSaved.getId());
        warehouseRepository.save(warehouse);
        return warehouseDto;
    }

    @Override
    public WarehouseDto update(WarehouseDto warehouseDto) {
        create(warehouseDto);
        return warehouseDto;
    }

    @Override
    public void deleteById(Long id) {
        warehouseRepository.deleteById(id);
    }
}
