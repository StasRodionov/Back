package com.trade_accounting.repositories.company;


import com.trade_accounting.models.entity.company.SaleTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTaxRepository extends JpaRepository<SaleTax, Long> {

    @Override
    @Query("SELECT c FROM SaleTax c WHERE c.id = :id")
    SaleTax getOne(@Param("id") Long id);
}
