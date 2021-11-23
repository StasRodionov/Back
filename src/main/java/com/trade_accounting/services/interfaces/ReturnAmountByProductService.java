package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.ReturnAmountByProductDto;


public interface ReturnAmountByProductService {

    ReturnAmountByProductDto findAmountByProductIdAndInvoiceId(Long productId, Long invoiceId);
    
}
