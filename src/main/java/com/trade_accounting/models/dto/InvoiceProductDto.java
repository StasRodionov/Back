package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceProductDto {

    private Long id;

    private InvoiceDto invoiceDto;

    private ProductDto productDto;

    private BigDecimal amount;

    private BigDecimal price;

    public InvoiceProductDto(Long id, Long invoiceId, Long productId, BigDecimal amount, BigDecimal price) {
        this.id = id;
        this.invoiceDto = new InvoiceDto();
        this.invoiceDto.setId(invoiceId);
        this.productDto = new ProductDto();
        this.productDto.setId(productId);
        this.amount = amount;
        this.price = price;
    }
}
