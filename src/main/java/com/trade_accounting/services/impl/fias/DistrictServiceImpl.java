package com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;
import com.trade_accounting.models.fias.District;
import com.trade_accounting.repositories.fias.DistrictRepository;
import com.trade_accounting.repositories.fias.RegionRepository;
import com.trade_accounting.services.interfaces.fias.DistrictService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DtoMapper dtoMapper;
    private final DistrictRepository repository;
    private final RegionRepository regionRepository;

    public DistrictServiceImpl(DtoMapper dtoMapper, DistrictRepository repository, RegionRepository regionRepository) {
        this.dtoMapper = dtoMapper;
        this.repository = repository;
        this.regionRepository = regionRepository;
    }

    @Override
    public List<DistrictDto> getAll() {
        List<District> districts = repository.findAll();
        return districts.stream().map(dtoMapper::toDistrictDto).collect(Collectors.toList());
    }

    @Override
    public DistrictDto getById(Long id) {
        District district = repository.findById(id).orElse(new District());
        return dtoMapper.toDistrictDto(district);
    }

    @Override
    public DistrictDto create(DistrictDto districtDto) {
        District district1 = dtoMapper.toDistrict(districtDto);
        District district = repository.save(district1);
        return dtoMapper.toDistrictDto(district);
    }

    @Override
    public DistrictDto update(DistrictDto districtDto) {
        return dtoMapper.toDistrictDto(repository.save(dtoMapper.toDistrict(districtDto)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
