package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoicesToCustomers;
import com.trade_accounting.models.dto.InvoicesToCustomersDto;
import com.trade_accounting.repositories.InvoicesToCustomerRepository;
import com.trade_accounting.services.interfaces.InvoicesToCustomersService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoicesToCustomersImpl implements InvoicesToCustomersService {

    private final InvoicesToCustomerRepository invoicesToCustomerRepository;
    private final DtoMapper dtoMapper;

    public InvoicesToCustomersImpl(InvoicesToCustomerRepository invoicesToCustomerRepository,
                                   DtoMapper dtoMapper) {
        this.invoicesToCustomerRepository = invoicesToCustomerRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<InvoicesToCustomersDto> getAll() {
        return invoicesToCustomerRepository.findAll().stream()
                .map(dtoMapper::InvoicesToCustomersToInvoicesToCustomersDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoicesToCustomersDto getById(Long id) {
        Optional<InvoicesToCustomers> invoicesToCustomers = invoicesToCustomerRepository.findById(id);
        return dtoMapper.InvoicesToCustomersToInvoicesToCustomersDto(invoicesToCustomers.orElse(new InvoicesToCustomers()));
    }

    @Override
    public InvoicesToCustomersDto create(InvoicesToCustomersDto dto) {
        InvoicesToCustomers saveInvoices = invoicesToCustomerRepository.save(
                dtoMapper.InvoicesToCustomersDtoToInvoicesToCustomers(dto));
        dto.setId(saveInvoices.getId());
        return dtoMapper.InvoicesToCustomersToInvoicesToCustomersDto(saveInvoices);
    }

    @Override
    public InvoicesToCustomersDto update(InvoicesToCustomersDto dto) {
        InvoicesToCustomers updateInvoices = invoicesToCustomerRepository.save(
                dtoMapper.InvoicesToCustomersDtoToInvoicesToCustomers(dto));
        return dtoMapper.InvoicesToCustomersToInvoicesToCustomersDto(updateInvoices);
    }

    @Override
    public void deleteById(Long id) {
        invoicesToCustomerRepository.deleteById(id);
    }

    @Override
    public List<InvoicesToCustomersDto> search(Specification<InvoicesToCustomers> spec) {
        return executeSearch(invoicesToCustomerRepository, dtoMapper::InvoicesToCustomersToInvoicesToCustomersDto, spec);
    }
}
