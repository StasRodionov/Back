package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAll();

    AddressDto getById(Long id);

    AddressDto create(AddressDto addressDto);

    AddressDto update(AddressDto addressDto);

    void deleteById(Long id);
}
