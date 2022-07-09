package com.trade_accounting.services.impl.units;

import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.models.entity.units.Country;
import com.trade_accounting.repositories.units.CountryRepository;
import com.trade_accounting.services.interfaces.units.CountryService;
import com.trade_accounting.utils.mapper.units.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getAll() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CountryDto getById(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        return countryMapper.toDto(countryOptional.orElse(new Country()));
    }

    @Override
    public CountryDto create(CountryDto dto) {
        Country country = countryRepository.save(countryMapper.toModel(dto));
        dto.setId(country.getId());
        return countryMapper.toDto(country);
    }

    @Override
    public CountryDto update(CountryDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public List<CountryDto> search(Specification<Country> spec) {
        return executeSearch(countryRepository, countryMapper::toDto, spec);
    }

    @Override
    public List<CountryDto> searchByString(String text) {
        return countryRepository.getBySearch(text)
                .stream()
                .map(countryMapper::toDto)
                .collect(Collectors.toList());
    }
}
