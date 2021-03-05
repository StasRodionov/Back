package com.trade_accounting.repositories;


import com.trade_accounting.models.Price;
import com.trade_accounting.models.dto.PriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

   // @Query("SELECT new com.trade_accounting.models.dto.PriceDto(p.id, p.product, p.typeOfPrice) from Price p")
   // List<PriceDto> getPricesByProductId(Long id);

    @Query("select p.prices from Product p where p.id = :id")
    List<Price> getPricesByProductId(Long id);
}
