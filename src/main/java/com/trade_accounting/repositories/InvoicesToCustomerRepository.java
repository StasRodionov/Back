package com.trade_accounting.repositories;

import com.trade_accounting.models.InvoicesToCustomers;
import com.trade_accounting.models.dto.InvoicesToCustomersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesToCustomerRepository extends JpaRepository<InvoicesToCustomers,Long>,
                                                      JpaSpecificationExecutor<InvoicesToCustomers> {

    @Query("select new com.trade_accounting.models.dto.InvoicesToCustomersDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.isSpend," +
            "e.comment) from InvoicesToCustomers  e")
    List<InvoicesToCustomersDto> getAll();


    @Query("select new com.trade_accounting.models.dto.InvoicesToCustomersDto (" +
            "e.id," +
            "e.date," +
            "e.company.id," +
            "e.warehouse.id," +
            "e.contract.id," +
            "e.isSpend," +
            "e.comment) from InvoicesToCustomers  e where  e.id = :id")
    InvoicesToCustomersDto getById(@Param("id") Long id);
}
