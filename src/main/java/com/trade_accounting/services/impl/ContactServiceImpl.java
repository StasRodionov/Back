package com.trade_accounting.services.impl;

import com.trade_accounting.models.Contact;
import com.trade_accounting.models.dto.ContactDto;
import com.trade_accounting.repositories.ContactRepository;
import com.trade_accounting.services.interfaces.ContactService;
import com.trade_accounting.utils.DtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final DtoMapper dtoMapper;
    private final ContactRepository contactRepository;

    public ContactServiceImpl(DtoMapper dtoMapper, ContactRepository contactRepository) {
        this.dtoMapper = dtoMapper;
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDto> getAll() {
        List<Contact> all = contactRepository.findAll();
        return all.stream().map(dtoMapper::contactToContactDto).collect(Collectors.toList());
    }

    @Override
    public ContactDto getById(Long id) {
        Contact contact = contactRepository.getOne(id);
        return dtoMapper.contactToContactDto(contact);
    }

    @Override
    public ContactDto create(ContactDto contactDto) {
        Contact contact = dtoMapper.contactDtoToContact(contactDto);
        Contact contactSaved = contactRepository.save(contact);
        contactDto.setId(contactSaved.getId());
        return contactDto;
    }

    @Override
    public ContactDto update(ContactDto contactDto) {
        Contact contact = dtoMapper.contactDtoToContact(contactDto);
        Contact save = contactRepository.save(contact);
        return dtoMapper.contactToContactDto(save);
    }


    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
