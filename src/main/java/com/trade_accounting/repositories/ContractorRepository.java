package com.trade_accounting.repositories;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.BankAccountDto;
import com.trade_accounting.models.dto.ContractorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "e.id," +
            "e.inn," +
            "e.name," +
            "e.sortNumber," +
            "e.phone," +
            "e.fax," +
            "e.email," +
            "e.address," +
            "e.commentToAddress," +
            "e.comment) from Contractor e")
    List<ContractorDto> getAll();


    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "e.id," +
            "e.inn," +
            "e.name," +
            "e.sortNumber," +
            "e.phone," +
            "e.fax," +
            "e.email," +
            "e.address," +
            "e.commentToAddress," +
            "e.comment) from Contractor e where e.id = :id")
    ContractorDto getById(@Param("id") Long id);

}
