package com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.models.fias.City;
import com.trade_accounting.repositories.fias.CityRepository;
import com.trade_accounting.repositories.fias.DistrictRepository;
import com.trade_accounting.services.interfaces.fias.CityService;
import com.trade_accounting.utils.DtoMapper;
import com.trade_accounting.utils.ModelDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final DtoMapper dtoMapper;
    private final CityRepository repository;
    private final DistrictRepository districtRepository;

    public CityServiceImpl(DtoMapper dtoMapper, CityRepository repository, DistrictRepository districtRepository) {
        this.dtoMapper = dtoMapper;
        this.repository = repository;
        this.districtRepository = districtRepository;
    }

    @Override
    public List<CityDto> getAll() {
        List<City> cityList = repository.findAll();
        return cityList.stream().map(dtoMapper::toCityDto).collect(Collectors.toList());
    }

    @Override
    public CityDto getById(Integer id) {
        City city = repository.findById(id).orElse(new City());
        return dtoMapper.toCityDto(city);
    }

    @Override
    public CityDto create(CityDto cityDto) {
        City city1 = dtoMapper.toCity(cityDto);
        city1.setDistrict(districtRepository.getOne(cityDto.getDistrictDto().getId()));
        City city = repository.save(city1);
        return dtoMapper.toCityDto(city);
    }

    @Override
    public CityDto update(CityDto cityDto) {
        City city = repository.save(ModelDtoConverter.toCity(cityDto));
        return dtoMapper.toCityDto(city);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
