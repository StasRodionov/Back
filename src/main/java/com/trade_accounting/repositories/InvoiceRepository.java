package com.trade_accounting.repositories;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.InvoiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("select new com.trade_accounting.models.dto.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.isSpend) from Invoice e")
    List<InvoiceDto> getAll();


    @Query("select new com.trade_accounting.models.dto.InvoiceDto(" +
            "e.id," +
            "e.date," +
            "e.typeOfInvoice," +
            "e.company.id," +
            "e.contractor.id," +
            "e.isSpend) from Invoice e where e.id = :id")
    InvoiceDto getById(@Param("id") Long id);
}
