package com.trade_accounting.repositories;

import com.trade_accounting.models.RetailSales;
import com.trade_accounting.models.RetailStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailSalesRepository extends JpaRepository<RetailSales, Long> {

}
