package com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.StreetDto;
import com.trade_accounting.models.fias.Street;
import com.trade_accounting.repositories.fias.CityRepository;
import com.trade_accounting.repositories.fias.StreetRepository;
import com.trade_accounting.services.interfaces.fias.StreetService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreetServiceImpl implements StreetService {
    private final DtoMapper dtoMapper;
    private final StreetRepository streetRepository;
    private final CityRepository cityRepository;

    public StreetServiceImpl(DtoMapper dtoMapper, StreetRepository streetRepository, CityRepository cityRepository) {
        this.dtoMapper = dtoMapper;
        this.streetRepository = streetRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<StreetDto> getAll() {
        List<Street> all = streetRepository.findAll();
        return all.stream().map(dtoMapper::toStreetDto).collect(Collectors.toList());
    }

    @Override
    public StreetDto getById(Integer id) {
        Street street = streetRepository.findById(id).orElse(new Street());
        return dtoMapper.toStreetDto(street);
    }

    @Override
    public StreetDto create(StreetDto streetDto) {
        Street street = dtoMapper.toStreet(streetDto);
        street.setCity(cityRepository.getOne(streetDto.getCityDto().getId()));
        Street save = streetRepository.save(street);
        return dtoMapper.toStreetDto(save);
    }

    @Override
    public StreetDto update(StreetDto streetDto) {
        Street street = streetRepository.save(dtoMapper.toStreet(streetDto));
        return dtoMapper.toStreetDto(street);
    }

    @Override
    public void deleteById(Integer id) {  //изменён с Integer
        streetRepository.deleteById(id);
    }
}
