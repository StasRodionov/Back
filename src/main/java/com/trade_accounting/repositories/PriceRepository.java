package com.trade_accounting.repositories;


import com.trade_accounting.models.Price;
import com.trade_accounting.models.dto.PriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT new com.trade_accounting.models.dto.PriceDto(price.id, price.value) " +
            "from Product product inner join product.prices as price where product.id = :id")
    List<PriceDto> getPricesDtoByProductId(Long id);

    @Query("select p.prices from Product p where p.id = :id")
    List<Price> getPricesByProductId(Long id);
}
