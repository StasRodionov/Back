package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.repositories.InvoiceProductRepository;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import com.trade_accounting.utils.mapper.InvoiceProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceProductMapper invoiceProductMapper;

    @Override
    public List<InvoiceProductDto> getAll() {
        List<InvoiceProduct> listInvoiceProductDto = invoiceProductRepository.findAll();
        return listInvoiceProductDto.stream().map(invoiceProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceProductDto> search(Specification<InvoiceProduct> spec) {
        return executeSearch(invoiceProductRepository, invoiceProductMapper::toDto, spec);
    }

    @Override
    public InvoiceProductDto getById(Long id) {
        Optional<InvoiceProduct> invoiceProductDto = invoiceProductRepository.findById(id);
        return invoiceProductMapper.toDto(invoiceProductDto.orElse(new InvoiceProduct()));
    }

    @Override
    public InvoiceProductDto create(@NotNull InvoiceProductDto invoiceProductDto) {
        InvoiceProduct invoiceProductSaved = invoiceProductRepository.save(invoiceProductMapper.toModel(invoiceProductDto));
        invoiceProductDto.setId(invoiceProductSaved.getId());
        return invoiceProductDto;
    }

    @Override
    public InvoiceProductDto update(@NotNull InvoiceProductDto invoiceProductDto) {
        invoiceProductRepository.save(invoiceProductMapper.toModel(invoiceProductDto));
        return invoiceProductDto;
    }

    @Override
    public void deleteById(Long id) {
        invoiceProductRepository.deleteById(id);
    }
}
