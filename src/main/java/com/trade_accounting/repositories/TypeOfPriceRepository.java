package com.trade_accounting.repositories;

import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.TypeOfPriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfPriceRepository extends JpaRepository<TypeOfPrice, Long> {

    @Query("select new com.trade_accounting.models.dto.TypeOfPriceDto(t.id, t.name, t.sortNumber) from TypeOfPrice as t")
    List<TypeOfPriceDto> getAll();

    @Query("select new com.trade_accounting.models.dto.TypeOfPriceDto(t.id, t.name, t.sortNumber) from TypeOfPrice as t where t.id = :id")
    TypeOfPriceDto getById(@Param("id") Long id);

}
