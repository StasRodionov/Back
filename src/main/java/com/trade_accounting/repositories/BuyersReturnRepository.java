package com.trade_accounting.repositories;

import com.trade_accounting.models.BuyersReturn;
import com.trade_accounting.models.dto.BuyersReturnDto;
import com.trade_accounting.models.dto.InvoiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyersReturnRepository extends JpaRepository<BuyersReturn, Long>, JpaSpecificationExecutor<BuyersReturn> {
    @Query("select new com.trade_accounting.models.dto.BuyersReturnDto(" +
            "e.id," +
            "e.date," +
            "e.warehouse.id," +
            "e.contractor.id," +
            "e.company.id," +
            "e.sum," +
            "e.isSent," +
            "e.isPrint," +
            "e.comment) from BuyersReturn e where e.contractor.id = :id")
    List<BuyersReturnDto> findByContractorId(Long id);
}
