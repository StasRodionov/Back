package com.trade_accounting.repositories;

import com.trade_accounting.models.Status;
import com.trade_accounting.models.dto.StatusDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("select new com.trade_accounting.models.dto.StatusDto(" +
            "s.id, " +
            "s.typeOfStatus) from Status s where s.id = :id")
    StatusDto getById(@Param("id") Long id);
}
