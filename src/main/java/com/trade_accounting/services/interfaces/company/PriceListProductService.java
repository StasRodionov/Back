package com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.PriceListProductDto;
import com.trade_accounting.models.entity.company.PriceListProduct;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PriceListProductService extends AbstractService<PriceListProductDto>,
        SearchableService<PriceListProduct, PriceListProductDto> {
    @Transactional
    List<PriceListProductDto> search(Specification<PriceListProduct> specification);
    List<PriceListProductDto> getAllByProductId(Long id);
    @Transactional
    default List<PriceListProductDto> searchByPriceListId(Long id) {
        return search((root, query, builder) -> builder.equal(root.get("priceList").get("id"), id));
    }
    void createAll(List<PriceListProductDto> priceListProductDtos);
}
