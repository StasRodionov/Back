package com.trade_accounting.repositories.company;


import com.trade_accounting.models.entity.company.SaleTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTaxRepository extends JpaRepository<SaleTax, Long> {
}
