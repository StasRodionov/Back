package com.trade_accounting.services.impl;

import com.trade_accounting.models.InvoiceProduct;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.repositories.InvoiceProductRepository;
import com.trade_accounting.repositories.InvoiceRepository;
import com.trade_accounting.repositories.ProductRepository;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final InvoiceProductRepository invoiceProductRepository;

    public InvoiceProductServiceImpl(InvoiceRepository invoiceRepository,
                                     ProductRepository productRepository,
                                     InvoiceProductRepository invoiceProductRepository) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.invoiceProductRepository = invoiceProductRepository;
    }

    @Override
    public List<InvoiceProductDto> getAll() {

        List<InvoiceProductDto> listInvoiceProductDto = invoiceProductRepository.getAll();
        for (InvoiceProductDto invoiceProductDto: listInvoiceProductDto){
            invoiceProductDto.setInvoiceDto(invoiceRepository.getById(invoiceProductDto.getInvoiceDto().getId()));
            invoiceProductDto.setProductDto(productRepository.getById(invoiceProductDto.getProductDto().getId()));
        }
        return listInvoiceProductDto;
    }

    @Override
    public InvoiceProductDto getById(Long id) {
        InvoiceProductDto invoiceProductDto = invoiceProductRepository.getById(id);
        invoiceProductDto.setInvoiceDto(invoiceRepository.getById(invoiceProductDto.getInvoiceDto().getId()));
        invoiceProductDto.setProductDto(productRepository.getById(invoiceProductDto.getProductDto().getId()));
        return invoiceProductDto;
    }

    @Override
    public void create(InvoiceProductDto invoiceProductDto) {
        invoiceProductRepository.save(new InvoiceProduct(
                invoiceRepository.getOne(invoiceProductDto.getInvoiceDto().getId()),
                productRepository.getOne(invoiceProductDto.getProductDto().getId()),
                invoiceProductDto.getAmount(),
                invoiceProductDto.getPrice()
        ));
    }

    @Override
    public void update(InvoiceProductDto invoiceProductDto) {
        invoiceProductRepository.save(new InvoiceProduct(
                invoiceProductDto.getId(),
                invoiceRepository.getOne(invoiceProductDto.getInvoiceDto().getId()),
                productRepository.getOne(invoiceProductDto.getProductDto().getId()),
                invoiceProductDto.getAmount(),
                invoiceProductDto.getPrice()
        ));

    }

    @Override
    public void deleteById(Long id) {
        invoiceProductRepository.deleteById(id);
    }
}
