package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {
    @Override
    List<ContactDto> getAll();
}
