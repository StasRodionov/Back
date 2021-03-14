package com.trade_accounting.repositories;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new com.trade_accounting.models.dto.ProductDto(" +
            "p.id, " +
            "p.name, " +
            "p.weight, " +
            "p.volume," +
            "p.purchasePrice," +
            "p.description, " +
            "p.archive) from Product p")
    List<ProductDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ProductDto(" +
            "p.id, " +
            "p.name, " +
            "p.weight, " +
            "p.volume," +
            "p.purchasePrice," +
            "p.description, " +
            "p.archive) from Product p where p.id = :id")
    ProductDto getById(@Param("id") Long id);


    @Query("SELECT new com.trade_accounting.models.dto.ProductDto(" +
            "p.id, " +
            "p.name, " +
            "p.weight, " +
            "p.volume," +
            "p.purchasePrice," +
            "p.description, " +
            "p.archive) from Product p "+
            "where p.productGroup.id = :id")
    List<ProductDto> getAllByProductGroupId(@Param("id") Long id);

    @Query("SELECT new com.trade_accounting.models.dto.ProductDto(" +
            "p.id, " +
            "p.name, " +
            "p.weight, " +
            "p.volume," +
            "p.purchasePrice," +
            "p.description, " +
            "p.archive) from Product p "+
            "where p.contractor.id = :id")
    List<ProductDto> getAllByContractorId(@Param("id") Long id);
}