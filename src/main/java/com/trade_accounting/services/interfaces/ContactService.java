package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {

    List<ContactDto> getAll();

    ContactDto getById(Long id);

  //  ContactDto create(ContactDto contactDto);

  //  ContactDto update(ContactDto contactDto);

    void deleteById(Long id);
}
