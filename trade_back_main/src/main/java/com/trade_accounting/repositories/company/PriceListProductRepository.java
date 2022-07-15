package com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.PriceListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListProductRepository extends JpaRepository<PriceListProduct, Long>,
        JpaSpecificationExecutor<PriceListProduct> {
    List<PriceListProduct> findAllByProductsId(Long id);

    @Query("select p from PriceListProduct p join fetch p.products " +
            "where lower ( concat(p.products.id, ' ', p.products.name)) " +
            "like lower(concat('%', :symbols, '%'))")
    List<PriceListProduct> getBySearch(@Param("symbols") String search);
}
