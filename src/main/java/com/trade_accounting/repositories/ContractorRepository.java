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
            "e.comment, " +
            "e.contractorGroup.id, " +
            "e.typeOfContractor.id, " +
            "e.typeOfPrice.id, " +
            "e.legalDetail.id) from Contractor e")
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
            "e.comment, " +
            "e.contractorGroup.id, " +
            "e.typeOfContractor.id, " +
            "e.typeOfPrice.id, " +
            "e.legalDetail.id) from Contractor e where e.id = :id")
    ContractorDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "p.contractor.id," +
            "p.contractor.inn," +
            "p.contractor.name," +
            "p.contractor.sortNumber," +
            "p.contractor.phone," +
            "p.contractor.fax," +
            "p.contractor.email," +
            "p.contractor.address," +
            "p.contractor.commentToAddress," +
            "p.contractor.comment, " +
            "p.contractor.contractorGroup.id, " +
            "p.contractor.typeOfContractor.id, " +
            "p.contractor.typeOfPrice.id, " +
            "p.contractor.legalDetail.id) from Product p where p.id = :id")
    ContractorDto getContractorById(@Param("id") Long id);
}
