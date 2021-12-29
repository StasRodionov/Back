package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.InvoiceToBuyerListProducts;
import com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InvoiceToBuyerListProductsService extends AbstractService<InvoiceToBuyerListProductsDto>,
        SearchableService<InvoiceToBuyerListProducts, InvoiceToBuyerListProductsDto> {

    @Transactional
    default List<InvoiceToBuyerListProductsDto> searchBySupplierId(Long id){
        return search((root, query, builder) -> builder.equal(root.get("supplierAccount").get("id"), id));
    }

    @Transactional
    List<InvoiceToBuyerListProductsDto> search(Specification<InvoiceToBuyerListProducts> specification);
}