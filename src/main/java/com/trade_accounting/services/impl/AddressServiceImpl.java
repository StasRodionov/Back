package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.Address;
import com.trade_accounting.models.BankAccount;
import com.trade_accounting.models.dto.AddressDto;
import com.trade_accounting.repositories.AddressRepository;
import com.trade_accounting.services.interfaces.AddressService;
import com.trade_accounting.utils.DtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private final DtoMapper dtoMapper;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(DtoMapper dtoMapper, AddressRepository addressRepository) {
        this.dtoMapper = dtoMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDto> getAll() {
        List<Address> all = addressRepository.findAll();
        return all.stream().map(dtoMapper::addressToAddressDto).collect(Collectors.toList());
    }

    @Override
    public AddressDto getById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            throw new NotFoundEntityException("There is not address with id " + id);
        }
        return dtoMapper.addressToAddressDto(address.get());

    }

    @Override
    public AddressDto create(AddressDto addressDto) {
        Address address = dtoMapper.addressDtoToAddress(addressDto);
        Address addressSaved = addressRepository.save(address);
        addressDto.setId(addressSaved.getId());
        return addressDto;
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        Address address = dtoMapper.addressDtoToAddress(addressDto);
        Address save = addressRepository.save(address);
        return dtoMapper.addressToAddressDto(save);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
