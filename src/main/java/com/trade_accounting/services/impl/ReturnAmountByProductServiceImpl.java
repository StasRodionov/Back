package com.trade_accounting.services.impl;

import com.trade_accounting.models.ReturnAmountByProduct;
import com.trade_accounting.models.dto.ReturnAmountByProductDto;
import com.trade_accounting.repositories.ReturnAmountByProductRepository;
import com.trade_accounting.services.interfaces.ReturnAmountByProductService;

import com.trade_accounting.utils.mapper.ReturnAmountByProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@AllArgsConstructor
public class ReturnAmountByProductServiceImpl implements ReturnAmountByProductService {

    private final ReturnAmountByProductRepository returnAmountByProductRepository;
    private final ReturnAmountByProductMapper returnAmountByProductMapper;

    @Override
    public ReturnAmountByProductDto findAmountByProductIdAndInvoiceId(Long productId, Long invoiceId) {
        ReturnAmountByProduct returnAmountByProduct =
                returnAmountByProductRepository.findAmountByProductIdAndInvoiceId(productId, invoiceId);

        return returnAmountByProduct.getId() != null
                ? returnAmountByProductMapper.toDto(returnAmountByProductRepository
                .findAmountByProductIdAndInvoiceId(productId, invoiceId))
                : ReturnAmountByProductDto
                .builder()
                .id(0L)
                .invoiceId(0L)
                .productId(0L)
                .contractorId(0L)
                .amount(BigDecimal.valueOf(0))
                .build();
    }

}
