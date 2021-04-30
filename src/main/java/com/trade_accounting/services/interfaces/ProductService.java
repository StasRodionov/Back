package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService extends AbstractService<ProductDto>, SearchableService<Product, ProductDto> {

    void deleteById(Long id);

    @Transactional
    default  List<ProductDto> getAllByProductGroupId(Long id) {
        return search((root, query, builder) ->
                builder.equal(root.get("productGroup").get("id"), id));
    }

    @Transactional
    default  List<ProductDto> getAllByContractorId(Long id) {
        return search((root, query, builder) ->
                builder.equal(root.get("contractor").get("id"), id));
    }

    List<ProductDto> search(String value);

}