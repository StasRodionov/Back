package com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.models.fias.Region;
import com.trade_accounting.repositories.fias.RegionRepository;
import com.trade_accounting.services.interfaces.fias.RegionService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {
    private final DtoMapper dtoMapper;
    private final RegionRepository repository;

    public RegionServiceImpl(DtoMapper dtoMapper, RegionRepository repository) {
        this.dtoMapper = dtoMapper;
        this.repository = repository;
    }

    @Override
    public List<RegionDto> getAll() {
        List<Region> regions = repository.findAll();
        return regions.stream().map(dtoMapper::toRegionDto).collect(Collectors.toList());
    }

    @Override
    public RegionDto getById(Integer id) {
        Region region = repository.findById(id).orElse(new Region());
        return dtoMapper.toRegionDto(region);
    }

    @Override
    public RegionDto create(RegionDto regionDto) {
        Region region = repository.save(dtoMapper.toRegion(regionDto));
        return dtoMapper.toRegionDto(region);
    }

    @Override
    public RegionDto update(RegionDto regionDto) {
        return create(regionDto);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
