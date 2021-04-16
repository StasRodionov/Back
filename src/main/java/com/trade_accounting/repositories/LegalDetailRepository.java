package com.trade_accounting.repositories;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.TypeOfPrice;
import com.trade_accounting.models.dto.LegalDetailDto;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LegalDetailRepository extends JpaRepository<LegalDetail, Long> {

    Optional<LegalDetail> findByInn(String name);

    @Query("select new com.trade_accounting.models.dto.LegalDetailDto(" +
            "e.id, " +
            "e.lastName, " +
            "e.firstName, " +
            "e.middleName, " +
            "e.address, " +
            "e.commentToAddress, " +
            "e.inn, " +
            "e.kpp, " +
            "e.okpo, " +
            "e.ogrn, " +
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
            "e.inn, e.kpp, " +
            "e.okpo, " +
            "e.ogrn, " +
            "e.numberOfTheCertificate, " +
            "e.dateOfTheCertificate, " +
            "e.typeOfContractor.id) from LegalDetail e where e.id = :id")
    LegalDetailDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.LegalDetailDto(" +
            "e.legalDetail.id, " +
            "e.legalDetail.lastName, " +
            "e.legalDetail.firstName, " +
            "e.legalDetail.middleName, " +
            "e.legalDetail.address, " +
            "e.legalDetail.commentToAddress, " +
            "e.legalDetail.inn, " +
            "e.legalDetail.kpp, " +
            "e.legalDetail.okpo, " +
            "e.legalDetail.ogrn, " +
            "e.legalDetail.numberOfTheCertificate, " +
            "e.legalDetail.dateOfTheCertificate, " +
            "e.legalDetail.typeOfContractor.id) from Contractor e where e.id = :id")
    LegalDetailDto getLegalDetailByContractorId(@Param("id") Long id);

//    Optional<LegalDetail> getEntityName(String name);
//    JpaPersistentEntity<LegalDetail>,

}
