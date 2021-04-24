package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.repositories.InvoiceProductRepository;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final DtoMapper dtoMapper;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository,
                                     DtoMapper dtoMapper) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<InvoiceProductDto> getAll() {
        List<InvoiceProduct> listInvoiceProductDto = invoiceProductRepository.findAll();
        return listInvoiceProductDto.stream().map(dtoMapper::invoiceProductToInvoiceProductDto)
                .collect(Collectors.toList());
    }
    public List<InvoiceProductDto> search(Specification<InvoiceProduct> spec) {
        return invoiceProductRepository.findAll(spec)
                .stream().map(dtoMapper::invoiceProductToInvoiceProductDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<InvoiceProductDto> searchByInvoiceId(Long id) {
        return search((root, query, builder) ->
                builder.equal(root.get("invoice").get("id"), id));
    }

    @Override
    public InvoiceProductDto getById(Long id) {
        Optional<InvoiceProduct> invoiceProductDto = invoiceProductRepository.findById(id);
        return dtoMapper.invoiceProductToInvoiceProductDto(invoiceProductDto.orElse(new InvoiceProduct()));
    }

    @Override
    public InvoiceProductDto create(@NotNull InvoiceProductDto invoiceProductDto) {
        invoiceProductRepository.save(dtoMapper.invoiceProductDtoToInvoiceProduct(invoiceProductDto));
        return invoiceProductDto;
    }

    @Override
    public InvoiceProductDto update(@NotNull InvoiceProductDto invoiceProductDto) {
        invoiceProductRepository.save(dtoMapper.invoiceProductDtoToInvoiceProduct(invoiceProductDto));
        return invoiceProductDto;
    }

    @Override
    public void deleteById(Long id) {
        invoiceProductRepository.deleteById(id);
    }
}
