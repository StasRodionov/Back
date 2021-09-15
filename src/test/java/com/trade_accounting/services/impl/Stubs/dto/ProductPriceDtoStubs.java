package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.ProductPriceDto;
import com.trade_accounting.models.dto.RetailSalesDto;
import com.trade_accounting.services.impl.Stubs.model.ProductPriceModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailSalesModelStubs;
import com.trade_accounting.utils.mapper.ProductPriceMapper;
import com.trade_accounting.utils.mapper.RetailSalesMapper;
import org.mapstruct.factory.Mappers;

public class ProductPriceDtoStubs {

    private static final ProductPriceMapper mapper = Mappers.getMapper(ProductPriceMapper.class);

    public static ProductPriceDto getDto(Long id) {
        return mapper.toDto(ProductPriceModelStubs.getProductPrice(id));
    }
}
