package com.trade_accounting.models.dto;

import com.trade_accounting.models.PostingProduct;
import com.trade_accounting.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostingProductInsideDto {
    private Long id;

    private PostingProductDto postingProductDto;

    private ProductDto productDto;

    private BigDecimal amount;

    private BigDecimal price;
}
