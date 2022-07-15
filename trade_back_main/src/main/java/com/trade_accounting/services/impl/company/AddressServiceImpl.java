package com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Address;
import com.trade_accounting.models.dto.company.AddressDto;
import com.trade_accounting.repositories.company.AddressRepository;
import com.trade_accounting.services.interfaces.company.AddressService;
import com.trade_accounting.utils.mapper.company.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAll() {
        List<Address> all = addressRepository.findAll();
        return all.stream().map(addressMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AddressDto getById(Long id) {
        Address address = addressRepository.getOne(id);
        AddressDto dto = new AddressDto(address.getId(), address.getIndex(), address.getCountry(), address.getRegion(), address.getCity(), address.getStreet(), address.getHouse(), address.getApartment(), address.getAnother());
        return dto;
    }

    @Override
    public AddressDto create(AddressDto addressDto) {
        Address address = new Address(addressDto.getId(), addressDto.getIndex(), addressDto.getCountry(), addressDto.getRegion(), addressDto.getCity(), addressDto.getStreet(), addressDto.getHouse(), addressDto.getApartment(), addressDto.getAnother());
        addressRepository.save(address);
        addressDto.setId(address.getId());
        return addressDto;
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        Address address = addressMapper.toModel(addressDto);
        Address save = addressRepository.save(address);
        return addressMapper.toDto(save);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
