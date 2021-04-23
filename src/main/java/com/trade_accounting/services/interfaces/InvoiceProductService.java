package com.trade_accounting.services.interfaces;


import com.trade_accounting.models.dto.InvoiceProductDto;

import java.util.List;

public interface InvoiceProductService extends AbstractService<InvoiceProductDto> {

    List<InvoiceProductDto> getByInvoiceId(Long id);
}
