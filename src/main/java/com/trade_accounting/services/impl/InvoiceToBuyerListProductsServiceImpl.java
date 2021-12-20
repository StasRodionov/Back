package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoiceToBuyerListProducts;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto;
import com.trade_accounting.repositories.InvoiceToBuyerListProductsRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.InvoiceToBuyerListProductsService;
import com.trade_accounting.services.interfaces.SupplierAccountService;
import com.trade_accounting.utils.mapper.InvoiceToBuyerListProductsMapper;
import com.trade_accounting.utils.mapper.SupplierAccountMapper;
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
public class InvoiceToBuyerListProductsServiceImpl implements InvoiceToBuyerListProductsService {
    private final InvoiceToBuyerListProductsRepository invoiceToBuyerListProductsRepository;
    private final InvoiceToBuyerListProductsMapper invoiceToBuyerListProductsMapper;
    private final ProductRepository productRepository;
    private final SupplierAccountService supplierAccountService;
    private final SupplierAccountMapper supplierAccountMapper;

    @Override
    public List<InvoiceToBuyerListProductsDto> getAll() {
        List<InvoiceToBuyerListProducts> invoiceToBuyerListProductsDtoList = invoiceToBuyerListProductsRepository.findAll();
        return invoiceToBuyerListProductsDtoList.stream().map(invoiceToBuyerListProductsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public InvoiceToBuyerListProductsDto getById(Long id) {
        Optional<InvoiceToBuyerListProducts> invoiceToBuyerListProductsDto = invoiceToBuyerListProductsRepository.findById(id);
        return invoiceToBuyerListProductsMapper.toDto(invoiceToBuyerListProductsDto.orElse(new InvoiceToBuyerListProducts()));
    }

    @Override
    public InvoiceToBuyerListProductsDto create(@NotNull InvoiceToBuyerListProductsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InvoiceToBuyerListProductsDto update(@NotNull InvoiceToBuyerListProductsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        invoiceToBuyerListProductsRepository.deleteById(id);
    }

    @Override
    public List<InvoiceToBuyerListProductsDto> search(Specification<InvoiceToBuyerListProducts> specification) {
        return executeSearch(invoiceToBuyerListProductsRepository, invoiceToBuyerListProductsMapper::toDto, specification);
    }

    private InvoiceToBuyerListProductsDto saveOrUpdate(InvoiceToBuyerListProductsDto dto) {
        Optional<Product> product = productRepository.findById(dto.getProductId());
        InvoiceToBuyerListProducts invoiceToBuyerListProducts = invoiceToBuyerListProductsMapper.toModel(dto);
        invoiceToBuyerListProducts.setSupplierAccount(supplierAccountMapper.toModel(supplierAccountService.getById(dto.getSupplierAccountId())));
        invoiceToBuyerListProducts.setProduct(product.orElse(null));
        return invoiceToBuyerListProductsMapper.toDto(invoiceToBuyerListProductsRepository.save(invoiceToBuyerListProducts));
    }
}
