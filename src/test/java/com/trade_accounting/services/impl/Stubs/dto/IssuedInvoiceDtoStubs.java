package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.IssuedInvoiceDto;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.services.impl.Stubs.model.IssuedInvoiceModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.IssuedInvoiceMapper;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.mapstruct.factory.Mappers;

public class IssuedInvoiceDtoStubs {
    private static final IssuedInvoiceMapper mapper = Mappers.getMapper(IssuedInvoiceMapper.class);

    public static IssuedInvoiceDto getDto(Long id) {
        return mapper.toDto(IssuedInvoiceModelStubs.getIssuedInvoice(id));
    }
}
