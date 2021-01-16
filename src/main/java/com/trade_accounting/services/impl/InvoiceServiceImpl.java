package com.trade_accounting.services.impl;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.services.interfaces.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<InvoiceDto> getAll() {
        return invoiceRepository.getAll();
    }

    @Override
    public InvoiceDto getById(Long id) {
        return invoiceRepository.getById(id);
    }

    @Override
    public void create(InvoiceDto invoiceDto) {
        invoiceRepository.save(
                new Invoice(
                        invoiceDto.getDate(),
                        invoiceDto.getTypeOfInvoice(),
                        invoiceDto.getCompany(),
                        invoiceDto.getContractor()));
    }

    @Override
    public void update(InvoiceDto invoiceDto) {
        invoiceRepository.save(
                new Invoice(
                        invoiceDto.getId(),
                        invoiceDto.getDate(),
                        invoiceDto.getTypeOfInvoice(),
                        invoiceDto.getCompany(),
                        invoiceDto.getContractor(),
                        invoiceDto.isSpend()));
    }

    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
