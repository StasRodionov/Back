package com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.PriceListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListProductRepository extends JpaRepository<PriceListProduct, Long>,
        JpaSpecificationExecutor<PriceListProduct> {
    List<PriceListProduct> findAllByProductsId(Long id);

}
