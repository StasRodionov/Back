package com.trade_accounting.repositories;

import com.trade_accounting.models.Product;
import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

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


    //    @Query("SELECT new com.trade_accounting.models.dto.ProductDto(" +
//            "p.id, " +
//            "p.name, " +
//            "p.weight, " +
//            "p.volume," +
//            "p.purchasePrice," +
//            "p.description, " +
//            "p.archive) from Product p "+
//            "where p.productGroup.id = :id")
    @Query("SELECT p from Product p " +
            "where p.productGroup.id = :id")
    List<Product> getAllByProductGroupId(@Param("id") Long id);

    //    @Query("SELECT new com.trade_accounting.models.dto.ProductDto(" +
//            "p.id, " +
//            "p.name, " +
//            "p.weight, " +
//            "p.volume," +
//            "p.purchasePrice," +
//            "p.description, " +
//            "p.archive) from Product p "+
//            "where p.contractor.id = :id")
    @Query("select p from Product  p " +
            "where p.contractor.id =:id")
    List<Product> getAllByContractorId(@Param("id") Long id);

    Optional<Product> findByName(String name);
}
