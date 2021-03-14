package com.trade_accounting.repositories;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.ContractorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long>, JpaSpecificationExecutor<Contractor> {

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "c.id," +
            "c.name," +
            "c.inn," +
            "c.sortNumber," +
            "c.phone," +
            "c.fax," +
            "c.email," +
            "c.address," +
            "c.commentToAddress," +
            "c.comment" +
            ") from Contractor c")
    List<ContractorDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "c.id," +
            "c.name," +
            "c.inn," +
            "c.sortNumber," +
            "c.phone," +
            "c.fax," +
            "c.email," +
            "c.address," +
            "c.commentToAddress," +
            "c.comment" +
            ") from Contractor c" +
            " where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "      or c.inn like concat('%', :searchTerm, '%')" +
            "      or c.phone like concat('%',  :searchTerm, '%')" +
            "      or c.comment like concat('%', :searchTerm, '%')"
    )
    List<ContractorDto> search(@Param("searchTerm") String searchTerm);

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "e.id," +
            "e.name," +
            "e.inn," +
            "e.sortNumber," +
            "e.phone," +
            "e.fax," +
            "e.email," +
            "e.address," +
            "e.commentToAddress," +
            "e.comment" +
            ") from Contractor e where e.id = :id")
    ContractorDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "p.contractor.id," +
            "p.contractor.name," +
            "p.contractor.inn," +
            "p.contractor.sortNumber," +
            "p.contractor.phone," +
            "p.contractor.fax," +
            "p.contractor.email," +
            "p.contractor.address," +
            "p.contractor.commentToAddress," +
            "p.contractor.comment" +
            ") from Product p where p.id = :id")
    ContractorDto getContractorById(@Param("id") Long id);
}
