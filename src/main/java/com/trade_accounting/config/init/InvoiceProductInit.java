package com.trade_accounting.config.init;

import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.models.dto.InvoiceProductDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.services.interfaces.InvoiceProductService;
import com.trade_accounting.services.interfaces.InvoiceService;
import com.trade_accounting.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceProductInit extends InitData {
    private final InvoiceProductService invoiceProductService;
    private final InvoiceService invoiceService;
    private final ProductService productService;


    @Override
    void init() {
        initInvoiceProducts();
    }

    private void initInvoiceProducts() {
        List<InvoiceDto> invoices = invoiceService.getAll().stream().limit(3).collect(Collectors.toList());
        List<ProductDto> products = productService.getAll().stream().limit(3).collect(Collectors.toList());

        for (InvoiceDto invoiceDto : invoices) {
            for (ProductDto productDto : products) {
                invoiceProductService.create(new InvoiceProductDto(
                        null,
                        invoiceDto.getId(),
                        productDto.getId(),
                        BigDecimal.valueOf(randomInt(20, 100)),
                        BigDecimal.valueOf(randomInt(30, 150))
                ));
            }
        }
    }
}

