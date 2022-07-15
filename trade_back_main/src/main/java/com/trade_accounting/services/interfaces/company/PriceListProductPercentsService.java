package com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.PriceListProductPercentsDto;
import com.trade_accounting.models.entity.company.PriceListProductPercents;
import com.trade_accounting.services.interfaces.util.AbstractService;
import com.trade_accounting.services.interfaces.util.SearchableService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PriceListProductPercentsService extends AbstractService<PriceListProductPercentsDto>, SearchableService<PriceListProductPercents, PriceListProductPercentsDto> {
    void createAll(List<PriceListProductPercentsDto> priceListProductPercentsDtos);
    List<PriceListProductPercentsDto> getAllByPriceListId(Long id);
    @Transactional
    default List<PriceListProductPercentsDto> searchByPriceListId(Long id) {
        return search((root, query, builder) -> builder.equal(root.get("priceList").get("id"), id));
    }
}
