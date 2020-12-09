package com.trade_accounting.repositories;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.LegalDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LegalDetailRepository extends JpaRepository<LegalDetail, Long> {
    @Query("select new com.trade_accounting.models.dto.LegalDetailDto(" +
            "e.id, " +
            "e.lastName, " +
            "e.firstName, " +
            "e.middleName, " +
            "e.address, " +
            "e.commentToAddress, " +
            "e.inn, " +
            "e.okpo, " +
            "e.ogrnip, " +
            "e.numberOfTheCertificate, " +
            "e.dateOfTheCertificate, " +
            "e.typeOfContractor.id) from LegalDetail e")
    List<LegalDetailDto> getAll();

    @Query("select new com.trade_accounting.models.dto.LegalDetailDto(" +
            "e.id, " +
            "e.lastName, " +
            "e.firstName, " +
            "e.middleName, " +
            "e.address, " +
            "e.commentToAddress, " +
            "e.inn, " +
            "e.okpo, " +
            "e.ogrnip, " +
            "e.numberOfTheCertificate, " +
            "e.dateOfTheCertificate, " +
            "e.typeOfContractor.id) from LegalDetail e where e.id = :id")
    LegalDetailDto getById(@Param("id") Long id);

}
