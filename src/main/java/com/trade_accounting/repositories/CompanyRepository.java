package com.trade_accounting.repositories;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.dto.CompanyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select new com.trade_accounting.models.dto.CompanyDto(" +
            "e.id, " +
            "e.name, " +
            "e.inn, " +
            "e.sortNumber, " +
            "e.phone, " +
            "e.fax, " +
            "e.email, " +
            "e.payerVat, " +
            "e.address, " +
            "e.commentToAddress, " +
            "e.leader, " +
            "e.leaderManagerPosition, " +
            "e.leaderSignature, " +
            "e.chiefAccountant, " +
            "e.chiefAccountantSignature, " +
            "e.stamp, " +
            "e.legalDetail.id) from Company e where e.email = :email")
    CompanyDto findByEmail(@Param("email") String email);

    @Query("select new com.trade_accounting.models.dto.CompanyDto(" +
            "e.id, " +
            "e.name, " +
            "e.inn, " +
            "e.sortNumber, " +
            "e.phone, " +
            "e.fax, " +
            "e.email, " +
            "e.payerVat, " +
            "e.address, " +
            "e.commentToAddress, " +
            "e.leader, " +
            "e.leaderManagerPosition, " +
            "e.leaderSignature, " +
            "e.chiefAccountant, " +
            "e.chiefAccountantSignature, " +
            "e.stamp, " +
            "e.legalDetail.id) from Company e")
    List<CompanyDto> getAll();

    @Query("select new com.trade_accounting.models.dto.CompanyDto(" +
            "e.id, " +
            "e.name, " +
            "e.inn, " +
            "e.sortNumber, " +
            "e.phone, " +
            "e.fax, " +
            "e.email, " +
            "e.payerVat, " +
            "e.address, " +
            "e.commentToAddress, " +
            "e.leader, " +
            "e.leaderManagerPosition, " +
            "e.leaderSignature, " +
            "e.chiefAccountant, " +
            "e.chiefAccountantSignature, " +
            "e.stamp, " +
            "e.legalDetail.id) from Company e where e.id = :id")
    CompanyDto getById(@Param("id") Long id);
}
