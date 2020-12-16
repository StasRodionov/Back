package com.trade_accounting.repositories;

import com.trade_accounting.models.TypeOfContractor;
import com.trade_accounting.models.dto.TypeOfContractorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfContractorRepository extends JpaRepository<TypeOfContractor, Long> {
    @Query("select new com.trade_accounting.models.dto.TypeOfContractorDto(" +
            "t.id, " +
            "t.name, " +
            "t.sortNumber" +
            ") " +
            "from TypeOfContractor t ")
    List<TypeOfContractorDto> getAll();

    @Query("select new com.trade_accounting.models.dto.TypeOfContractorDto(" +
            "t.id, " +
            "t.name, " +
            "t.sortNumber" +
            ") " +
            "from TypeOfContractor t " +
            "where t.id=:id")
    TypeOfContractorDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.TypeOfContractorDto(" +
            "t.typeOfContractor.id, " +
            "t.typeOfContractor.name, " +
            "t.typeOfContractor.sortNumber" +
            ") " +
            "from Contractor t " +
            "where t.id=:id")
    TypeOfContractorDto getTypeOfContractorByContractorId(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.TypeOfContractorDto(" +
            "t.id, " +
            "t.name, " +
            "t.sortNumber" +
            ") " +
            "from TypeOfContractor t " +
            "where t.name=:name")
    TypeOfContractorDto getByName(String name);
}
