package com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.dto.company.ContactDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    //Contact
    Contact toModel(ContactDto contactDto);

    ContactDto toDto(Contact contact);
}
