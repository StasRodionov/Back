package com.trade_accounting.models.dto;

import com.trade_accounting.models.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class InvoiceProductDto {

    private Long id;

    private InvoiceDto invoiceDto;

    private ProductDto productDto;

    private BigDecimal amount;

    private BigDecimal price;

    public InvoiceProductDto(InvoiceDto invoiceDto,
                             ProductDto productDto,
                             BigDecimal amount,
                             BigDecimal price) {
        this.invoiceDto = invoiceDto;
        this.productDto = productDto;
        this.amount = amount;
        this.price = price;
    }

    public InvoiceProductDto(Long id,
                             InvoiceDto invoiceDto,
                             ProductDto productDto,
                             BigDecimal amount,
                             BigDecimal price) {
        this(invoiceDto, productDto, amount, price);
        this.id = id;
    }

}
