package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
