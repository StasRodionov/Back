package com.trade_accounting.repositories;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Product;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.ProductDto;
import com.trade_accounting.models.dto.TypeOfPriceDto;
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
            "p.description, " +
            "p.archive, " +
            "p.purchasePrice, " +
            "p.weight, " +
            "p.volume) from Product p")
    List<ProductDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ProductDto(p.id, " +
            "p.name, " +
            "p.description, " +
            "p.archive, " +
            "p.purchasePrice, " +
            "p.weight, " +
            "p.volume) from Product p where p.id = :id")
    ProductDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductDto(p.images) from Product p where p.id = :id")
    List<ImageDto> getProductByImageDto(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductDto(p.typeOfPrices) from Product p where p.id = :id")
    List<TypeOfPriceDto> getProductByTypeOfPriceDto(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductDto(p.images) from Product p where p.id = :id")
    List<Image> getProductByImage(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ProductDto(p.typeOfPrices) from Product p where p.id = :id")
    List<TypeOfPrice> getProductByTypeOfPrice(@Param("id") Long id);
}