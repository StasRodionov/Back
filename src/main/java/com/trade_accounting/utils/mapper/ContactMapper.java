package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Contact;
import com.trade_accounting.models.dto.ContactDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    //Contact
     ContactDto toDto(Contact contact);

     Contact toModel(ContactDto contactDto);

     List<Contact> toListModel(List<ContactDto> contactDtoList);

     List<ContactDto> toListDto(List<Contact> contactList);
}
