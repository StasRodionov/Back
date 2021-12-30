package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.SupplierAccountProductsList;
import com.trade_accounting.models.dto.SupplierAccountProductsListDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SupplierAccountProductsListService extends AbstractService<SupplierAccountProductsListDto>,
        SearchableService<SupplierAccountProductsList, SupplierAccountProductsListDto> {

        @Transactional
        default List<SupplierAccountProductsListDto> searchBySupplierId(Long id){
            return search((root, query, builder) -> builder.equal(root.get("supplierAccount").get("id"), id));
        }

        @Transactional
        List<SupplierAccountProductsListDto> search(Specification<SupplierAccountProductsList> specification);
}
