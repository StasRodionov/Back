package com.trade_accounting.repositories;

import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.PositionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query("select new com.trade_accounting.models.dto.PositionDto(p.id, p.name, p.sortNumber) from Position p")
    List<PositionDto> getAll();

    @Query("select new com.trade_accounting.models.dto.PositionDto(p.id, p.name, p.sortNumber) from Position p where p.id = :id")
    PositionDto getById(@Param("id") Long id);
}



