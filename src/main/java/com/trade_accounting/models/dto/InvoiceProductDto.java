package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProductDto {

    private Long id;

    private String invoiceDto; // todo: заменить String на InvoiceDto после реализации InvoiceDto

    private ProductDto productDto;

    private BigDecimal amount;

    private BigDecimal price;

    public InvoiceProductDto(String invoiceDto, // todo: заменить String на InvoiceDto после реализации InvoiceDto
                             ProductDto productDto,
                             BigDecimal amount,
                             BigDecimal price) {
        this.invoiceDto = invoiceDto;
        this.productDto = productDto;
        this.amount = amount;
        this.price = price;
    }

    public InvoiceProductDto(Long id,
                             String invoiceDto, // todo: заменить String на InvoiceDto после реализации InvoiceDto
                             ProductDto productDto,
                             BigDecimal amount,
                             BigDecimal price) {
        this(invoiceDto, productDto, amount, price);
        this.id = id;
    }

}
